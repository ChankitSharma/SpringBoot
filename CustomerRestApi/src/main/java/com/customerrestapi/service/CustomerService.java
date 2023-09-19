package com.customerrestapi.service;

import java.util.List;

import com.customerrestapi.dto.CustomerDto;
import com.customerrestapi.entites.Customer;

/**
 * This interface is used to perform customer related crud operation
 * 
 * @Author ChankitSharma
 */
public interface CustomerService {

	public CustomerDto saveDetails(CustomerDto customerDto);

	public List<Customer> getAllCustomer();

	public Customer getCustomerById(Long id);

	public void deleteById(Long id);

	public String deleteAllCustomer();

	public void updateDetails(CustomerDto customerDto);

}