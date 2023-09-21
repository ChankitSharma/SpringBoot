package com.customerrestapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customerrestapi.constant.MessageConstant;
import com.customerrestapi.dto.CustomerDto;
import com.customerrestapi.entites.Customer;
import com.customerrestapi.exception.CustomeException;
import com.customerrestapi.service.CustomerService;
import org.modelmapper.ModelMapper;

/**
 * Controller class for handling customer-related operations.
 * 
 * @Author ChankitSharma
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	CustomerService customerService;

	/**
	 * Creates a new customer record.
	 *
	 * @param customer The customer object to be created.
	 * @return A message indicating the successful creation of the customer.
	 */
	@PostMapping("/customer")
	public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto customerDto) throws CustomeException {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveDetails(customerDto));
	}

	/**
	 * Retrieves a list of all customer records.
	 *
	 * @return A response containing a list of customer details, or a "not found"
	 *         message if no records exist.
	 */
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerDto>> read() {
		List<Customer> customerDetails = customerService.getAllCustomer();

		if (customerDetails == null) {
			throw new CustomeException(MessageConstant.ID_NOT_FOUND);
		} else {
			// Use ModelMapper to map the list of Customer entities to CustomerDto objects
			List<CustomerDto> customerDtos = customerDetails.stream()
					.map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(customerDtos);
		}
	}

	/**
	 * Retrieves a customer record by its unique identifier.
	 *
	 * @param id The ID of the customer to retrieve.
	 * @return A response containing the customer details if found, or a "not found"
	 *         message if the customer does not exist.
	 */
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
		Customer customerDetails = customerService.getCustomerById(id);

		// Check if the customer was found
		if (customerDetails == null) {
			throw new CustomeException(MessageConstant.ID_NOT_FOUND);
		}

		CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	/**
	 * Updates an existing customer record.
	 *
	 * @param customerDto The updated customer data.
	 * @return A response indicating the success or failure of the update operation.
	 */
	@PutMapping("/customer")
	public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto) {
		customerService.updateDetails(customerDto);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	/**
	 * Deletes a customer record by its unique identifier.
	 *
	 * @param id The ID of the customer to delete.
	 * @return A response indicating the success or failure of the delete operation.
	 */
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
		try {
			customerService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(MessageConstant.CUSTOMER_DELETED_MESSAGE + id);
		} catch (CustomeException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	/**
	 * Deletes all customer records.
	 *
	 * @return A response indicating the success or failure of the delete operation.
	 */
	@DeleteMapping("/customer")
	public ResponseEntity<String> deleteAllCustomers() {
		try {
			String responseMessage = customerService.deleteAllCustomer();
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		} catch (RuntimeException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

}