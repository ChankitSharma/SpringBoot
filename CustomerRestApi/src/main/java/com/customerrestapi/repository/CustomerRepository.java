package com.customerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerrestapi.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}