package com.registration.controller;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registration.entity.Customer;
import com.registration.exception.CustomerNotFoundException;
import com.registration.service.CustomerService;

/**
 * Controller class for handling customer-related operations.
 * 
 * @Author ChankitSharma
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Displays the view for customer registration form.
	 *
	 * @return The name of the view template for customer registration form.
	 */
	@RequestMapping("/viewForm")
	public String viewCreateForm() {
		return "customerRegistration";
	}

	/**
	 * Saves a new customer's information.
	 *
	 * @param customer The customer entity to be saved.
	 * @return Redirects to the customer list page.
	 */
	@RequestMapping("/save")
	public String saveAs(Customer customer) {
		customerService.save(customer);
		return "redirect:/customerList";
	}

	/**
	 * Retrieves a list of customers and sorts them alphabetically by first name and
	 * then by last name.
	 * 
	 * @param model The Spring model to hold the customer list.
	 * @return The name of the view template for the customer list.
	 */
	@RequestMapping("/customerList")
	public String getAllCustomers(Model model) {
		List<Customer> list = customerService.findAllCustomers();
		list.sort(Comparator.comparing(Customer::getFirstName).thenComparing(Customer::getLastName));
		model.addAttribute("list", list);
		return "customerList";
	}

	/**
	 * Deletes a customer by their ID and updates the customer list.
	 *
	 * @param id    The ID of the customer to be deleted.
	 * @param model The Spring model to hold the updated customer list.
	 * @return The name of the view template for the updated customer list.
	 */
	@PostMapping("/delete")
	public String deleteCustomerList(@RequestParam("id") long id, Model model) {
		customerService.deleteCustomer(id);
		List<Customer> list = customerService.findAllCustomers();
		model.addAttribute("list", list);
		return "redirect:/customerList";
	}

	/**
	 * Retrieves a customer by their ID and prepares for updating.
	 *
	 * @param id    The ID of the customer to be updated.
	 * @param model The Spring model to hold the customer to be updated.
	 * @return The name of the view template for updating customer information.
	 */
	@RequestMapping("/update")
	public String updateCustomerList(@RequestParam("id") long id, Model model) {
		Customer customer = customerService.updateCustomer(id);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with ID: " + id);
		}
		model.addAttribute("customer", customer);
		return "customerRegistration";
	}

	/**
	 * Checks the availability of an email address.
	 * 
	 * @param email The email address to check.
	 * @return ResponseEntity with a response indicating whether the email is
	 *         available or taken.
	 */
	@RequestMapping("/checkEmailAvailability")
	public ResponseEntity<String> checkEmailAvailability(@RequestParam("email") String email,
			@RequestParam(value = "id", required = false) Long id) {
		if (id == null) {
			// If id is not provided, simply check if any customer with the same email
			// exists
			boolean isEmailAvailable = customerService.isEmailAvailable(email);
			if (isEmailAvailable) {
				return ResponseEntity.ok("available");
			} else {
				return ResponseEntity.ok("taken");
			}
		} else {
			// If id is provided, check if any customer with the same email but a different
			// id exists
			boolean isEmailAvailable = customerService.findByEmailAndIdNotEquals(email, id);
			if (isEmailAvailable) {
				return ResponseEntity.ok("available");
			} else {
				return ResponseEntity.ok("taken");
			}
		}
	}

	/**
	 * Checks the availability of a mobile number.
	 * 
	 * @param mobile The mobile number to check.
	 * @return ResponseEntity with a response indicating whether the mobile number
	 *         is available or unavailable.
	 */
	@RequestMapping("/checkMobileAvailability")
	public ResponseEntity<String> checkMobileAvailability(@RequestParam("mobile") Long mobile) {
		boolean isMobileAvailable = customerService.isMobileAvailable(mobile);
		if (isMobileAvailable) {
			return ResponseEntity.ok("available");
		} else {
			return ResponseEntity.ok("unavailable");
		}
	}

}