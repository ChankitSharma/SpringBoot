package com.registration.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.entity.Customer;
import com.registration.exception.CustomerNotFoundException;
import com.registration.repository.CustomerRepository;

/**
 * Implementation of the CustomerService interface.
 * 
 * @Author ChankitSharma
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> findAll = customerRepository.findAll();
		return findAll;
	}

	@Override
	public void deleteCustomer(long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		
		if (customerOptional.isPresent()) {
			// Customer found, delete it
			customerRepository.deleteById(id);
		} else {
			// Handle the case when no customer with the given ID is found
			throw new CustomerNotFoundException("Customer not found for deleting with ID: " + id);
		}
	}

	@Override
	public Customer updateCustomer(long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		
		if (customerOptional.isPresent()) {
			return customerOptional.get(); // Value is present, return it
		} else {
			// Handle the case when no customer with the given ID is found
			throw new CustomerNotFoundException("Customer not found with ID: " + id);
		}
	}

	@Override 
	public boolean isEmailAvailable(String email) { 
		Optional<Customer>customer = customerRepository.findByEmail(email); 
		return !customer.isPresent(); }

	@Override
	public boolean isMobileAvailable(Long mobile) {
		Optional<Customer> customer = customerRepository.findByMobile(mobile);
		return !customer.isPresent();
	}

	@Override
	public boolean findByEmailAndIdNotEquals(String email, Long id) {
		Optional<Customer> customer = customerRepository.findByEmailAndIdNot(email, id);
		return !customer.isPresent();
	}

}