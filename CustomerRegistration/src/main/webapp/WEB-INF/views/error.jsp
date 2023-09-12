<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Error</title>
	<style>
	/* Add your custom CSS styling here */
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
			text-align: center;
		}
		
		.error-container {
			margin: 50px auto;
			padding: 20px;
			background-color: #fff;
			border: 1px solid #ccc;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
			max-width: 800px;
		}
		
		.error-heading {
			font-size: 24px;
			font-weight: bold;
			margin-bottom: 20px;
			color: red;
		}
		
		.error-message {
			font-size: 18px;
			overflow: hidden;
			max-height: 100px;
			word-wrap: break-word;
		}
		.return-button {
			display: inline-block;
			padding: 10px 20px;
			background-color: #007bff;
			color: #fff;
			text-decoration: none;
			border-radius: 5px;
			font-weight: bold;
			margin-top: 20px;
			transition: background-color 0.3s;
		}
	</style>
</head>
<body>
	<div class="error-container">
		<h2 class="error-heading">*Something Went Wrong*</h2>
		<p class="error-message">${errorMessage}</p>
	</div>
	<a href="/customerList" class="return-button">Back</a>
</body>
</html>