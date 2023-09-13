package com.customerrestapi.entites;

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

@Entity
@Getter @Setter public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", columnDefinition = "VARCHAR(45)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "VARCHAR(45)")
	private String lastName;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(columnDefinition = "VARCHAR(255)")
	private String email;
}