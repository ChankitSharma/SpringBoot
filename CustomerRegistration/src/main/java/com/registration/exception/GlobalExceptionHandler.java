package com.registration.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	* Exception handler for CustomerNotFoundException.
	* Handles the specific exception by rendering an "error" view with a custom error message.
	*
	* @param exception The CustomerNotFoundException to be handled.
	* @return A ModelAndView object that represents the error view.
	*/
	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handleCustomerNotFoundException(CustomerNotFoundException exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	/**
	* Generic exception handler for handling unexpected exceptions.
	* Handles exceptions of type Exception or its subclasses by rendering an "error" view
	* with a generic error message.
	*
	* @param exception The unexpected exception to be handled.
	* @return A ModelAndView object that represents the error view.
	*/
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", "An unexpected error occurred: " + exception.getMessage());
		return modelAndView;
	}

}