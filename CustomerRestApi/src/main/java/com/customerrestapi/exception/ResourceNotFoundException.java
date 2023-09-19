package com.customerrestapi.exception;

/**
 * The ResourceNotFoundException is a custom exception class that extends the
 * RuntimeException. It is used to represent the situation where a requested
 * resource is not found in the system.
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ResourceNotFoundException with the specified error message.
	 *
	 * @param message The error message that describes why the resource was not
	 *                found.
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}