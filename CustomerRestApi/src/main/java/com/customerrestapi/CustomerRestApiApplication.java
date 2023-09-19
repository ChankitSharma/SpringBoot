package com.customerrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerRestApiApplication {

	/**
	 * The main method to start the Customer Rest API application.
	 *
	 * @param args Command line arguments (if any).
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomerRestApiApplication.class, args);
	}

	/**
	 * Creates and configures a ModelMapper bean.
	 *
	 * @return A ModelMapper instance for object mapping.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}