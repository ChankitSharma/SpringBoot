package com.customerrestapi.exception;

/**
 * The EmailOrMobileAlreadyExists is a custom exception class that extends the
 * RuntimeException. It is used to represent the situation where an email or
 * mobile number already exists in the system, and a unique constraint is
 * violated.
 *
 * @Author ChankitSharma
 */
public class EmailOrMobileAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailOrMobileAlreadyExists(String message) {
		super(message);
	}

}