<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Customer List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
/* Additional custom CSS styles */
.truncate-text {
	max-width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.container {
	padding-top: 20px;
}

.add-button {
	position: absolute;
	top: 20;
	right: 0;
	margin: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

.action-links {
	display: inline-block;
	margin-right: 10px;
	text-decoration: none;
}

.center-text {
	text-align: center;
}

.id-text {
	text-align: center;
}
/* Styles for the confirmation popup */
.confirm-popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: honeydew;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	z-index: 1000;
}

.confirm-popup h2 {
	margin-top: 0;
	text-align: center;
}

.confirm-popup-buttons {
	margin-top: 15px;
	text-align: center;
}

.confirm-popup-buttons button {
	margin: 0 10px;
	padding: 5px 15px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

.confirm-popup-buttons .confirm {
	background-color: #dc3545;
	color: white;
}

.confirm-popup-buttons .cancel {
	background-color: #28a745;
	color: white;
}
</style>
<script>
	function confirmDelete(customerId) {
		var popup = document.getElementById('confirmPopup');
		var confirmBtn = document.getElementById('confirmBtn');
		var cancelBtn = document.getElementById('cancelBtn');

		// Show the confirmation popup
		popup.style.display = 'block';

		// Handle confirm button click
		confirmBtn.onclick = function () {
			// Create a form dynamically
			var form = document.createElement('form');
			form.method = 'post';
			form.action = '/delete';

			// Create an input element to hold the customer ID
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'id';
			input.value = customerId;

			// Append the input to the form
			form.appendChild(input);

			// Append the form to the body and submit it
			document.body.appendChild(form);
			form.submit();

			// Hide the confirmation popup
			popup.style.display = 'none';
		};

		// Handle cancel button click
		cancelBtn.onclick = function () {
			popup.style.display = 'none';
		};
	}
</script>

</head>
<body>
	<div class="container">
		<div class="add-button">
			<a href="/viewForm" class="btn btn-primary">Add New Customer</a>
		</div>
	</div>
	<div>
		<h1 class="center-text">Customer List</h1>
	</div>
	<table>
		<thead>
			<tr>
				<th class="id-text">ID</th>
				<th>Name</th>
				<th>Date of Birth</th>
				<th>Mobile</th>
				<th>Address</th>
				<th>Gender</th>
				<th>Email</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="customer" varStatus="loop">
				<tr>
					<td class="id-text">${loop.index + 1}</td>
					<td>${customer.firstName} ${customer.lastName}</td>
					<td><fmt:formatDate value="${customer.dateOfBirth}"
							pattern="dd/MM/yyyy" /></td>
					<td>${customer.mobile}</td>
					<td class="truncate-text"
						title="${customer.addressOne}, ${customer.addressTwo}">
						${customer.addressOne}, ${customer.addressTwo}</td>
					<td>${customer.gender}</td>
					<td class="truncate-text" title="${customer.email}">${customer.email}</td>
					<td><a class="btn btn-warning" href="javascript:void(0);"
						onclick="confirmDelete(${customer.id})">Delete</a></td>
						<td>
						<form method="post" action="/update">
							<input type="hidden" name="id" value="${customer.id}">
							<button type="submit" class="btn btn-warning" >Edit</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Are you sure you want to delete this
					record?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<a id="deleteLink" href="#" class="btn btn-danger"
						onclick="confirmDelete()">Delete</a>
				</div>
			</div>
		</div>
	</div>
</body>
<div id="confirmPopup" class="confirm-popup">
	<h2>Confirm Deletion</h2>
	<p>Are you sure you want to delete this customer?</p>
	<div class="confirm-popup-buttons">
		<button id="confirmBtn" class="confirm">Confirm</button>
		<button id="cancelBtn" class="cancel">Cancel</button>
	</div>
</div>
</html>