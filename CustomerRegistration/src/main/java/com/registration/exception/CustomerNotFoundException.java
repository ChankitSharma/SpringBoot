package com.registration.exception;

/**
 * Custom Exception Class: CustomerNotFoundException
 *
 * This class represents a custom runtime exception used to indicate that a customer
 * with a specific identifier or criteria could not be found.
 *
 * It extends the RuntimeException class, making it an unchecked exception.
 * This allows it to be thrown without the need for explicit exception handling
 * in methods that may encounter customer-related errors.
 *
 * The serialVersionUID is set to 1L to provide version control for the class
 * during serialization and deserialization.
 *
 * @author ChankitSharma
 * @version 1.0
 */
public class CustomerNotFoundException extends RuntimeException {
	/**
	* Constructor with a custom error message.
	*
	* @param message A descriptive error message explaining the reason for the exception.
	*/
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}