package com.customerrestapi.exception;

public class EmailIsAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailIsAlreadyExists(String message) {
		super(message);
	}
}