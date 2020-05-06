<%@page import="model.PaymentScheme"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!--  -->


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="Components/payment-scheme.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="Views/stylesheet.css">

<title>Payment Schemes</title>

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
					id="hidPSIDSave" name="hidPSIDSave" value="" />
			</form>
		</div>
		<div id="alertSuccess" class="alert alert-success" style="margin-top:50px;"></div>
		<div id="alertError" class="alert alert-danger" style="margin-top:50px;"></div>
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