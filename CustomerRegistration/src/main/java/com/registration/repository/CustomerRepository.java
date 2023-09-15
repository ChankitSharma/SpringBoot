package com.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.entity.Customer;

/**
 * Repository interface for managing Customer entities.
 * 
 * @Author ChankitSharma
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByMobile(Long mobile);

	Optional<Customer> findByEmailAndIdNot(String email, Long id);

	Optional<Customer> findByEmail(String email);

}