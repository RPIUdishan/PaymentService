<%@page import="model.PaymentScheme"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment-scheme.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="Views/stylesheet.css">
<title>Insert title here</title>
</head>
<header>
	<div class="system-name">
		<h1>Hospital Management System</h1>
	</div>
	<div class="service-name">
		<h3>Payment Schemes Management</h3>
	</div>
</header>
<body>
	<div class="container">
		<div class="psForm">
			<h5>Add Your Payment Schemes Here</h5>

			<form id="ps-form" name="ps-form" method="post" action="index.jsp">
				Doctor's ID: <input id="docID" name="docID" type="text"
					class="form-control form-control-sm" /> <br /> Hospital's ID: <input
					id="hospitalID" name="hospitalID" type="text"
					class="form-control form-control-sm" /> <br /> Doctor's Charge: <input
					id="docCharge" name="docCharge" type="number"
					class="form-control form-control-sm" /> <br /> Hospital's Charge:
					<input id="hospCharge" name="hospCharge" type="text"
					class="form-control form-control-sm" /> <br /> Tax: <input
					id="tax" name="tax" type="number"
					class="form-control form-control-sm" /> <br /> <input
					id="btnSave" name="btnSave" type="button" value="Save"
					class="btn btn-outline-success" /> <input type="hidden"
					id="hidItemIDSave" name="hidItemIDSave" value="" />
			</form>
		</div>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
	</div>

	<div class="container">

		<div id="divPSGrid">

			<%
				PaymentScheme ps = new PaymentScheme();
 				out.print(ps.viewAllPaymentSchemes());
			 %>
			 
		</div>
	</div>
</body>

</html>