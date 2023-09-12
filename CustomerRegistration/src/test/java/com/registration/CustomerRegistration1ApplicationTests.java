package com.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.registration.repository.CustomerRepository;

@SpringBootTest
class CustomerRegistration1ApplicationTests {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Test
	void contextLoads() {
		System.out.println(customerRepo);
	}

}