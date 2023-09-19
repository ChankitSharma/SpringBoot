package com.customerrestapi.dto;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.customerrestapi.constant.MessageConstant;
import com.customerrestapi.gender.Gender;

import lombok.Getter;
import lombok.Setter;

/**
 * This is dto class which is used for validation and transfer the data in object.
 * 
 * @Author ChankitSharma
 */
@Getter
@Setter
public class CustomerDto {

	private Long id;

	@NotNull(message = MessageConstant.FIRST_NAME_NOT_NULL)
	@NotEmpty(message = MessageConstant.FIRST_NAME_REQUIRED)
	@Size(min = 3, max = 20, message = MessageConstant.FIRST_NAME_SIZE)
	private String firstName;

	@NotEmpty(message = MessageConstant.LAST_NAME_REQUIRED)
	@Size(min = 3, max = 20, message = MessageConstant.LAST_NAME_SIZE)
	private String lastName;

	@NotNull(message = MessageConstant.DOB_REQUIRED_MESSAGE) 
	private Date dateOfBirth;

	@NotNull(message = MessageConstant.MOBILE_REQUIRED)
	@Pattern(regexp = "\\d{10}", message = MessageConstant.MOBILE_INVALID)
	@Size(min = 10,max = 10, message = MessageConstant.MOBILE_SIZE)
	private String mobile;

	@NotEmpty(message = MessageConstant.ADDRESS_REQUIRED)
	@Size(max = 255, message = MessageConstant.ADDRESS_SIZE)
	private String addressOne;

	@NotEmpty(message = MessageConstant.ADDRESS_REQUIRED)
	@Size(max = 255, message = MessageConstant.ADDRESS_SIZE)
	private String addressTwo;

	@NotNull(message = MessageConstant.GENDER_REQUIRED)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotEmpty(message = MessageConstant.EMAIL_REQUIRED)
	@Email(message = MessageConstant.EMAIL_INVALID)
	private String email;

}