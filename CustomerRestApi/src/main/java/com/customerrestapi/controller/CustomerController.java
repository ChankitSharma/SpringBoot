package com.customerrestapi.controller;

import java.util.List;

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
import com.customerrestapi.exception.EmailOrMobileAlreadyExists;
import com.customerrestapi.exception.ResourceNotFoundException;
import com.customerrestapi.service.CustomerService;

/**
 * Controller class for handling customer-related operations.
 * 
 * @Author ChankitSharma
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/**
	 * Creates a new customer record.
	 *
	 * @param customer The customer object to be created.
	 * @return A message indicating the successful creation of the customer.
	 */
	@PostMapping("/customer")
	public ResponseEntity<Object> create(@Valid @RequestBody CustomerDto customerDto) {
		try {
			customerService.saveDetails(customerDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstant.SAVE_SUCCESS_MESSAGE);
		} catch (EmailOrMobileAlreadyExists exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	/**
	 * Retrieves a list of all customer records.
	 *
	 * @return A response containing a list of customer details, or a "not found"
	 *         message if no records exist.
	 */
	@GetMapping("/customer")
	public ResponseEntity<Object> read() {
		List<Customer> customerDetails = customerService.getAllCustomer();

		if (customerDetails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstant.RECORD_NOT_FOUND_MESSAGE);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
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
	public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
		try {
			Customer customerDetails = customerService.getCustomerById(id);
			return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
		} catch (ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	/**
	 * Updates an existing customer record.
	 *
	 * @param customerDto The updated customer data.
	 * @return A response indicating the success or failure of the update operation.
	 */
	@PutMapping("/customer")
	public ResponseEntity<Object> update(@Valid @RequestBody CustomerDto customerDto) {
		try {
			customerService.updateDetails(customerDto);
			return ResponseEntity.status(HttpStatus.OK).body(MessageConstant.UPDATE_SUCCESS_MESSAGE);
		} catch (EmailOrMobileAlreadyExists exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
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
		} catch (ResourceNotFoundException exception) {
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