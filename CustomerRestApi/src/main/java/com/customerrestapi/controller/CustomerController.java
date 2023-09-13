package com.customerrestapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.customerrestapi.entites.Customer;
import com.customerrestapi.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository customerrepository;

	@PostMapping("/customer")
	public String createCustomer(@RequestBody Customer customer) {
		customerrepository.save(customer);
		return "Customer created in database";
	}
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> customer = new ArrayList<>();
		customerrepository.findAll().forEach(customer:: add);
		return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
		Optional<Customer> emp = customerrepository.findById(id);
		if(emp.isPresent()) {
			return new ResponseEntity<Customer>(emp.get(),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/customer/{id}")
	public String updateCustomerById(@PathVariable long id, @RequestBody Customer customer) {
		Optional<Customer> emp = customerrepository.findById(id);
		if(emp.isPresent()) {
			Customer existEmp = emp.get();
			existEmp.setFirstName(customer.getFirstName());
			existEmp.setLastName(customer.getLastName());
			existEmp.setEmail(customer.getEmail());
			existEmp.setGender(customer.getGender());
			customerrepository.save(existEmp);
			return "Customer details is updated";
		}else {
			return "Customer details are not found";
		}
	}
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		customerrepository.deleteById(id);
		return "customer deleted";
	}
	@DeleteMapping("/customer")
	public String deleteAllCustomer() {
		customerrepository.deleteAll();
		return "All Customer Deleted";
	}
}