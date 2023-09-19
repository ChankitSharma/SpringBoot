package com.customerrestapi.entites;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.customerrestapi.gender.Gender;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a customer entity with registration information.
 * 
 * @Author ChankitSharma
 */
@Entity
@Getter 
@Setter 
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", columnDefinition = "VARCHAR(45)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "VARCHAR(45)")
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "mobile_number", length = 10, unique = true, nullable = false)
	private String mobile;

	@Column(name = "address_one", length = 255, nullable = false)
	private String addressOne;

	@Column(name = "address_two", length = 255, nullable = false)
	private String addressTwo;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(columnDefinition = "VARCHAR(255)")
	private String email;

}