package com.registration.service;

import java.util.List;

import com.registration.entity.Customer;
import com.registration.exception.CustomerNotFoundException;

/**
 * Service interface for managing customer-related operations.
 * 
 * @Author ChankitSharma
 */
public interface CustomerService {

	/**
	 * Saves a customer entity.
	 *
	 * @param customer The customer entity to be saved.
	 */
	public void save(Customer customer);

	/**
	 * Retrieves a list of all customers.
	 *
	 * @return A list of all customers.
	 */
	List<Customer> findAllCustomers();

	/**
	 * Deletes a customer by their ID.
	 *
	 * @param id The ID of the customer to be deleted.
	 */
	public void deleteCustomer(long id) throws CustomerNotFoundException;

	/**
	 * Updates a customer's information.
	 *
	 * @param id The ID of the customer to be updated.
	 * @return The updated customer entity.
	 */
	public Customer updateCustomer(long id);

	/**
	* Checks the availability of a mobile number.
	*
	* @param mobile The mobile number to check.
	* @return true if the mobile number is available; false otherwise.
	*/
	boolean isMobileAvailable(Long mobile);

	/**
	* Checks the availability of an email address basis on id.
	*
	* @param email The email address to check.
	* @param id    The ID of the customer to exclude from the check (use null for new customer checks).
	* @return true if the email address is available; false otherwise.
	*/
	public boolean findByEmailAndIdNotEquals(String email, Long id);

	/**
	* Checks the availability of an email address.
	*
	* @param email The email address to check.
	* @return true if the email address is available; false otherwise.
	*/
	public boolean isEmailAvailable(String email);

}