package com.registration.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.registration.gender.Gender;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a customer entity with registration information.
 * 
 * @Author ChankitSharma
 */
@Entity
@Getter @Setter public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required")
	@Column(name = "first_name", columnDefinition = "VARCHAR(45)")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 255, message = "Last name must not exceed 255 characters")
	@Column(name = "last_name", columnDefinition = "VARCHAR(45)")
	private String lastName;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	@Column(columnDefinition = "BIGINT")
	private Long mobile;

	private String addressOne;

	private String addressTwo;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(columnDefinition = "VARCHAR(255)")
	private String email;

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

}