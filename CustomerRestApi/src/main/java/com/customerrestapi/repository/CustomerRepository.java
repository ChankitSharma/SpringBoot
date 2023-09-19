package com.customerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerrestapi.entites.Customer;

/**
 * This interface is used to extend Jpa repository to perform crud operation and helps to define custom query.
 * 
 * @Author ChankitSharma
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	boolean existsByEmailAndIdNotOrMobileAndIdNot(String email, Long emailId, String mobile, Long mobileId);

}