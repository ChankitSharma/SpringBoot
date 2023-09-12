<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Customer Registration</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
/* Additional custom CSS styles */
	body {
		background-color: #f8f9fa;
		margin: 0;
		padding: 0;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.registration-form {
		max-width: 100%;
		margin: 0 auto;
		padding: 20px;
		background-color: #ffffff;
		border-radius: 5px;
		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	}

	.form-container {
		margin: 0;
		width: 100%;
		max-width: 100%;
		padding: 20px;
		background-color: #ffffff;
		border-radius: 5px;
		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	}

	.center-content {
		text-align: center;
		margin-bottom: 20px;
		margin: 17px;
	}

	.heading-container {
		text-align: center;
	}

	#firstnameError, #lastnameError, #mobileError, #addressOneError,
		#addressTwoError, #emailError, #dobError, #genderError {
		color: red;
	}

	@media ( max-width : 576px) {
		.registration-form {
			margin: 0;
			max-width: 100%;
			padding: 10px;
		}
		.form-container {
			overflow: auto;
			position: fixed;
			top: 0;
			z-index: 1;
		}
	}

	@media ( min-width : 577px) and (max-width: 992px) {
		.col-md-8 {
			flex: 100%;
			max-width: 100%;
		}
		.registration-form {
			margin: 0;
			max-width: 100%;
			padding: 10px;
		}
		.form-container {
			overflow: auto;
		}
		.container {
			max-width: 100%;
			margin-top: 25px;
			position: sticky;
			top: 0;
			z-index: 1;
		}
	}

	@media ( min-width : 993px) {
		.registration-form {
			margin: 0;
			padding: 10px;
		}
		.container {
			margin: 0;
			margin-top: 25px;
			position: sticky;
			top: 0;
			z-index: 1;
		}
		.form-container {
			overflow: auto;
		}
		input[type="date"]::-webkit-calendar-picker-indicator {
			display: none;
		}
		input[readonly].form-control {
			background-color: #ffffff;
			/* Match the background color of other fields */
			border: 1px solid #ced4da;
			/* Match the border style of other fields */
			cursor: pointer; /* Show the pointer cursor when hovering */
		}
	
		/* Style for the readonly input field when hovered */
		input[readonly].form-control:hover {
			background-color: #ffffff;
			/* Match the background color of other fields on hover */
		}

		/* Style for the readonly input field when focused */
		input[readonly].form-control:focus {
			background-color: #ffffff;
			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
			border-color: #ced4da;
			/* Match the border color of other fields when focused */
			cursor: pointer;
		}
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="registration-form">
					<div class="heading-container">
						<c:choose>
							<c:when test="${not empty customer.id}">
								<h2>Edit Customer Information</h2>
							</c:when>
							<c:otherwise>
								<h2>Register a New Customer</h2>
							</c:otherwise>
						</c:choose>
					</div>
					<form action="save" method="post">
						<div class="form-group">
							<input type="hidden" name="id" value="${customer.id}" /> <label
								for="firstname">First Name</label> <input type="text"
								class="form-control" name="firstName" id="firstname"
								value="${customer.firstName}"
								placeholder="Enter your First Name" autocomplete="off">
							<span id="firstnameError"></span>
						</div>

						<div class="form-group">
							<label for="lastname">Last Name</label> <input type="text"
								class="form-control" name="lastName" id="lastname"
								value="${customer.lastName}" placeholder="Enter your Last Name"
								autocomplete="off"> <span id="lastnameError"></span>
						</div>

						<div class="form-group">
							<label for="dateOfBirth">Date Of Birth</label> <input type="text"
								class="form-control" name="dateOfBirth" id="dateOfBirth"
								value="${customer.dateOfBirth}" placeholder="DD/MM/YYYY"
								autocomplete="off" readonly> <span id="dobError"></span>
						</div>

						<div class="form-group" id="ageField">
							<label for="age">Age :</label> <span id="ageValue"></span>
						</div>

						<div class="form-group">
							<label for="mobile">Mobile</label> <input type="tel"
								class="form-control" name="mobile" id="mobile"
								value="${customer.mobile}"
								placeholder="Enter your Mobile Number" data-original-mobile="${customer.mobile}" autocomplete="off">
							<span id="mobileError"></span>
						</div>

						<div class="form-group">
							<label for="addressOne">Address One</label> <input type="text"
								class="form-control" name="addressOne" id="addressOne"
								value="${customer.addressOne}"
								placeholder="Enter your Address One" maxlength="255"
								autocomplete="off"> <span id="addressOneError"></span>
						</div>

						<div class="form-group">
							<label for="addressTwo">Address Two</label> <input type="text"
								class="form-control" name="addressTwo" maxlength="255"
								id="addressTwo" value="${customer.addressTwo}"
								placeholder="Enter your Address Two" autocomplete="off">
							<span id="addressTwoError"></span>
						</div>

						<div class="form-group">
							<label for="gender">Gender</label> <select class="form-control"
								id="gender" name="gender">
								<option value="Male"
									${customer.gender == 'Male' ? 'selected' : ''}>Male</option>
								<option value="Female"
									${customer.gender == 'Female' ? 'selected' : ''}>Female</option>
								<option value="Other"
									${customer.gender == 'Other' ? 'selected' : ''}>Other</option>
							</select> <span id="genderError"></span>
						</div>

						<div class="form-group">
							<label for="email">Email</label> <input type="email"
								class="form-control" name="email" id="email"
								value="${customer.email}" placeholder="Enter your email"
								data-original-email="${customer.email}"> <span
								id="emailError"></span>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-primary" id="submit"
								disabled>Submit</button>
							<a href="/customerList" class="btn btn-secondary">View
								Customer List</a>
						</div>
					</form>
				</div>
			</div>
			<script>
			$(document).ready(function () {
				console.log("JavaScript is running...");

				function formatDateForSubmit(dateString) {
					var parts = dateString.split("/");
					var year = parts[2];
					var month = parts[1];
					var day = parts[0];
					return year + "-" + month + "-" + day;
				}

				$("#dateOfBirth").datepicker({
					dateFormat: 'dd/mm/yy',
					changeMonth: true,
					changeYear: true,
					maxDate: 0,
					 yearRange: "1600:c"
					/* yearRange: "-100:+0" */
				});

				var dobInput = $("#dateOfBirth");
				if (dobInput.val() !== "") {
					var parts = dobInput.val().split("-");
					var year = parts[0];
					var month = parts[1];
					var day = parts[2];
					var formattedDate = day + "/" + month + "/" + year;
					dobInput.val(formattedDate);
				}

				function validateForm() {
					var isValid =
					isValidFirstName() &&
					isValidLastName() &&
					isValidMobile() &&
					isValidAddressOne() &&
					isValidAddressTwo() &&
					isValidEmail()&&
					isValidDateOfBirth()&&
					isValidGender();

					if (isValid) {
				$('#submit').removeAttr('disabled');
					} else {
				$('#submit').prop('disabled', true);
					}
					return isValid;
				}

				function isValidFirstName() {
					var firstName = $('#firstname').val();
					if (firstName === "") {
				 $('#firstnameError').text('*Please enter a first name.');
				 return false;
					} else if (!/^[A-Z][a-zA-Z]*$/.test(firstName)) {
				 $('#firstnameError').text('*Name must start with a capital letter and should not contain digits.');
				 return false;
					} else if (firstName.length < 3 || firstName.length > 45) {
				 $('#firstnameError').text('*Name must be between 3 and 45 letters.');
				 return false;
					} else {
				 $('#firstnameError').text('');
				 return true;
					}
				}

				function isValidLastName() {
					var lastName = $('#lastname').val();
					if (lastName === "") {
				 $('#lastnameError').text('*Please enter a last name.');
				 return false;
					} else if (!/^[A-Z][a-zA-Z]*$/.test(lastName)) {
				 $('#lastnameError').text('*Last Name must start with a capital letter and should not contain digits.');
				 return false;
					} else if (lastName.length < 3 || lastName.length > 45) {
				 $('#lastnameError').text('*Name must be between 3 and 45 letters.');
				 return false;
					} else {
				 $('#lastnameError').text('');
				 return true;
					}
				}

				function isValidDateOfBirth() {
					var dob = $('#dateOfBirth').val();
					if (dob === "") {
						$('#dobError').text('*Please enter your Date of Birth.');
						return false;
					} else {
						$('#dobError').text('');
						console.log("isValidDateOfBirth() called with non-empty date of birth");
						return true;
					}
				}

				function isValidMobile() {
					var mobile = $('#mobile').val();
					if (!/^\d{10}$/.test(mobile)) {
				 $('#mobileError').text('*Mobile number must be exactly 10 digits.');
				 return false;
					} else {
				 $('#mobileError').text('');
				 return true;
					}
				}

				function isValidAddressOne() {
					var addressOne = $('#addressOne').val();
					if (addressOne.length < 1) {
						$('#addressOneError').text('*Please enter an address.');
						return false;
					} else if (addressOne.length > 255) {
						$('#addressOneError').text('*Address must not exceed 255 characters.');
						return false;
					} else {
						$('#addressOneError').text('');
						return true;
					}
				}

				function isValidAddressTwo() {
					var addressTwo = $('#addressTwo').val();
					if (addressTwo.length < 1) {
				 $('#addressTwoError').text('*Please enter an address.');
				 return false;
					} else if (addressTwo.length > 255) {
						$('#addressTwoError').text('*Address must not exceed 255 characters.');
						return false;
					} else {
				 $('#addressTwoError').text('');
				 return true;
					}
				}

				function isValidEmail() {
					var email = $('#email').val();
					var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/i;
					if (!email.match(emailPattern)) {
						$('#emailError').text('*Please enter a valid email address.');
						return false;
					} else {
						// Check if the email field has a 'data-original-email' attribute
						var originalEmail = $('#email').attr('data-original-email');
						if (originalEmail && originalEmail.toLowerCase() === email.toLowerCase()) {
							$('#emailError').text(''); // Clear the error message
						} else {
							checkEmailAvailability(email); // Perform email availability check
						}
						return true;
					}
				}
				function isValidGender() {
					var gender = $('#gender').val();
					if (gender === "") {
						$('#genderError').text('*Please select a gender.');
						return false;
					} else {
						$('#genderError').text('');
						return true;
					}
				}
				$("#firstname").on("keyup", function () {
					isValidFirstName();
					validateForm();
				});
				$("#lastname").on("keyup", function () {
					isValidLastName();
					validateForm();
				});
				
				$("#dateOfBirth").on("keyup", function () {
					isValidDateOfBirth();
					validateForm();
				});
				$("#dateOfBirth").on("change", function () {
					isValidDateOfBirth();
					calculateAge();
					validateForm();
				});
				$("#mobile").on("keyup", function () {
					var mobile = $(this).val();
					if (mobile !== "") {
						var originalMobile = $(this).data("original-mobile");
						if (mobile !== originalMobile) {
							checkMobileAvailability(mobile);
						} else {
							$('#mobileError').text('');
							$('#submit').removeAttr('disabled');
						}
					} else {
						$('#mobileError').text('');
						$('#submit').removeAttr('disabled');
					}
				});
				$("#mobile").on("keyup", function () {
					isValidMobile();
					validateForm();
				});
				$("#addressOne").on("keyup", function () {
					isValidAddressOne();
					validateForm();
				});
				$("#addressTwo").on("keyup", function () {
					isValidAddressTwo();
					validateForm();
				});
				$("#email").on("keyup", function () {
					isValidEmail();
					validateForm();
				});
				$("#gender").on("keyup change", function () {
					isValidGender();
					validateForm();
				});

				function checkEmailAvailability(email) {
					var id = $('#id').val();
					$.ajax({
						url: "checkEmailAvailability",
						type: "GET",
						data: {
							email: email,
							id: id
						},
						success: function (response) {
							if (response === "available") {
								$('#emailError').text('');
								$('#submit').prop('disabled');
								validateForm();
							} else {
								$('#emailError').text('*Email is already taken.');
								$('#submit').prop('disabled', true);
							}
						},
						error: function (xhr, textStatus, errorThrown) {
							console.error(errorThrown);
						}
					});
				}

				function checkMobileAvailability(mobile) {
					$.ajax({
						url: "checkMobileAvailability",
						type: "GET",
						data: { mobile: mobile },
						success: function (response) {
							console.log("Response from server:", response);

							if (response === "available") {
								$('#mobileError').text('');
								validateForm();
							} else {
								var originalMobile = $('#mobile').attr('data-original-mobile');
								if (response === "unavailable" && mobile === originalMobile) {
									$('#mobileError').text('');
								} else {
									$('#mobileError').text('*Mobile number is already taken.');
									$('#submit').prop('disabled', true);
								}
							}
						},
						error: function (xhr, textStatus, errorThrown) {
							console.error(errorThrown);
						}
					});
				}

				function calculateAge() {
					var dobInput = document.getElementById("dateOfBirth");
					var ageInput = document.getElementById("ageValue");
					var ageField = document.getElementById("ageField");
					var dobErrorSpan = document.getElementById("dobError");

					if (dobInput.value !== "") {
						var dobParts = dobInput.value.split("/");
						var dobDay = parseInt(dobParts[0]);
						var dobMonth = parseInt(dobParts[1]) - 1;
						var dobYear = parseInt(dobParts[2]);

						var dob = new Date(dobYear, dobMonth, dobDay);
						var currentDate = new Date();

						if (dob > currentDate) {
							dobErrorSpan.textContent = "Date of Birth cannot be a future date.";
							ageInput.textContent = "";
							ageField.style.display = "none";
						} else {
							var ageYearDiff = currentDate.getFullYear() - dobYear;
							var ageMonthDiff = currentDate.getMonth() - dobMonth;
							var ageDayDiff = currentDate.getDate() - dobDay;

							if (ageDayDiff < 0) {
								ageMonthDiff -= 1;
								ageDayDiff += new Date(currentDate.getFullYear(), currentDate.getMonth(), 0).getDate();
							}

							if (ageMonthDiff < 0) {
								ageYearDiff -= 1;
								ageMonthDiff += 12;
							}

							ageInput.textContent = ageYearDiff + " years " + ageMonthDiff + " months";
							ageField.style.display = "inline";
							dobErrorSpan.textContent = "";
						}
					} else {
						ageInput.textContent = "";
						ageField.style.display = "none";
						dobErrorSpan.textContent = "";
					}
				}

				calculateAge();

				$("#mobile").on("keypress", function (event) {
					var keyCode = event.which ? event.which : event.keyCode;
					var contactValue = $(this).val();
					if (keyCode >= 48 && keyCode <= 57) {
						if ((contactValue + String.fromCharCode(keyCode)).length > 10) {
							event.preventDefault();
						}
					} else {
						event.preventDefault();
					}
				});

				$("#firstname,#lastname").on("keypress", function (event) {
					var keyCode = event.which ? event.which : event.keyCode;

					if (
						(keyCode >= 65 && keyCode <= 90) ||
						(keyCode >= 97 && keyCode <= 122) ||
						keyCode === 8
					) {
						var input = $(this).val();
						if (input.length >= 45) {
							event.preventDefault();
						}
					} else {
						event.preventDefault();
					}
				});

				$("#age").prop("disabled", true);

				$('#submit').click(function () {
					var dobInput = $("#dateOfBirth");
					var formattedDate = formatDateForSubmit(dobInput.val());
						dobInput.val(formattedDate);
				});
			});
		</script>
		</div>
	</div>
</body>
</html>