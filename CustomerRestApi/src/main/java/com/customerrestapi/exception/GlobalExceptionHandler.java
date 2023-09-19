package com.customerrestapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The GlobalExceptionHandler class provides commonly give exception and handling for
 * the REST API. It captures and processes exceptions that occur during API
 * requests and returns appropriate error responses to the client.
 *
 * @Author ChankitSharma
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * This method is used to handle bad-request or validation and show error message in post man response body
	 * @param exception
	 * @return response error message
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception) {
		Map<String, String> response = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

}