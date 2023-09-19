package com.customerrestapi.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerrestapi.constant.MessageConstant;
import com.customerrestapi.dto.CustomerDto;
import com.customerrestapi.entites.Customer;
import com.customerrestapi.exception.EmailOrMobileAlreadyExists;
import com.customerrestapi.exception.ResourceNotFoundException;
import com.customerrestapi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Save customer details.
	 * 
	 * @param customerDto The CustomerDto object containing customer information.
	 * @return The saved CustomerDto object.
	 * @throws EmailOrMobileAlreadyExists if the provided email or mobile already
	 *                                    exists for another customer.
	 */
	@Override
	public CustomerDto saveDetails(CustomerDto customerDto) {
		Long emailId = customerDto.getId();
		Long mobileId = customerDto.getId();

		// Check if email or mobile already exists for other customers
		boolean existsEmailOrMobile = customerRepository.existsByEmailAndIdNotOrMobileAndIdNot(customerDto.getEmail(),
				emailId, customerDto.getMobile(), mobileId);
		if (existsEmailOrMobile) {
			throw new EmailOrMobileAlreadyExists(MessageConstant.EMAIL_MOBILE_EXISTS_MESSAGE);
		}
		// Map DTO to entity and save it
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customerRepository.save(customer);

		// Map the saved entity back to DTO and return it
		return modelMapper.map(customer, CustomerDto.class);
	}

	/**
	 * Get a list of all customers.
	 * 
	 * @return List of all customers.
	 */
	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	/**
	 * Get a customer by their ID.
	 * 
	 * @param id The ID of the customer to retrieve.
	 * @return The customer with the specified ID.
	 * @throws ResourceNotFoundException if the customer with the specified ID is
	 *         not found.
	 */
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MessageConstant.ID_NOT_FOUND));
	}

	/**
	 * Delete a customer by their ID.
	 * 
	 * @param id The ID of the customer to delete.
	 * @throws ResourceNotFoundException if the customer with the specified ID is
	 *         not found.
	 */
	@Override
	public void deleteById(Long id) {
		if (!customerRepository.existsById(id)) {
			throw new ResourceNotFoundException(MessageConstant.ID_NOT_FOUND);
		}
		customerRepository.deleteById(id);
	}

	/**
	 * Delete all customers.
	 * 
	 * @return A message indicating the result of the operation.
	 * @throws RuntimeException if no customers were found to delete.
	 */
	@Override
	public String deleteAllCustomer() {
		long deletedCount = customerRepository.count();

		if (deletedCount > 0) {
			customerRepository.deleteAll();
			return MessageConstant.DELETE_ALL_CUSTOMER;
		} else {
			throw new RuntimeException(MessageConstant.RECORD_NOT_FOUND);
		}
	}

	@Override
	public void updateDetails(@Valid CustomerDto customerDto) {
		// First, check if the customer with the given ID exists in the database.
		Long customerId = customerDto.getId();
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

		// Update the customer's details with the information from customerDto.
		// Here, you might want to update specific fields such as name, address, etc.
		existingCustomer.setFirstName(customerDto.getFirstName());
		existingCustomer.setLastName(customerDto.getLastName());
		existingCustomer.setDateOfBirth(customerDto.getDateOfBirth());
		existingCustomer.setMobile(customerDto.getMobile());
		existingCustomer.setAddressOne(customerDto.getAddressOne());
		existingCustomer.setAddressTwo(customerDto.getAddressTwo());
		existingCustomer.setGender(customerDto.getGender());
		existingCustomer.setEmail(customerDto.getEmail());

		// Save the updated customer to the database.
		customerRepository.save(existingCustomer);

	}

}