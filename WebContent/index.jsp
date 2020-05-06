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

	<script>
	
	$(document).ready(function(){
		
		$("#alertSuccess").hide();
		$("#alertError").hide();
		
	});



	//=======================SAVE==================
	$(document).on("click", "#btnSave", function(event){
		
		console.log("Pressed");
		
		//clear alerts
		$("#alertSuccess").text("");
		$("#alertSuccess").hide;
		$("#alertError").text();
		$("#alertError").hide();
		
		//Form Validation
		var status = validateForm();

		if(status != true){
			
			$("#alertError").text(status);
			$("#alertError").show();
			
			return;
			
		}
		
		$("ps-form").submit();
		
		var type = ($("#hidPSIDSave").val() == "") ? "POST" : "PUT";
		$.ajax(
				{
				 url : "PaymentSchemeAPI",
				 type : type,
				 data : $("#ps-form").serialize(),
				 dataType : "text",
				 complete : function(response, status)
				 {
					 onPaymentSchemeSaveComplete(response.responseText, status);
				 }
				});
	});




	function validateForm(){
		
		//doctor's ID validation
		if($("#docID").val().trim() == ""){
			return "Insert Doctor's ID Number...";
		}
		
		
		
		var temp1 = $("#docID").val().trim();
		if(!$.isNumeric(temp1)){
			return "ID type incorrect"
		}
		
		//hospital's ID validation
		if($("#hospitalID").val().trim() == ""){
			return "Insert Hospital's ID Number...";
		}
		
		var temp2 = $("#hospitalID").val().trim();
		if(!$.isNumeric( temp2 )){
			return "ID type incorrect"
		}
		
		
		//doctors charge validation
		if($("#docCharge").val().trim() == ""){
			return "Insert Hospital's ID Number...";
		}
		
		var temp3 = $("#docCharge").val().trim();
		if(!$.isNumeric(temp3)){
			return "Incorrect..."
		}
		
		$("#docCharge").val(parseFloat(temp3).toFixed(2));
		
		//hospital's charge validation
		if($("#hospCharge").val().trim() == ""){
			return "Insert Hospital Charge...";
		}
		
		var temp4 = $("#hospCharge").val().trim();
		if(!$.isNumeric(temp4)){
			return "Incorrect..."
		}
		
		$("#hospCharge").val(parseFloat(temp4).toFixed(2));
		
		
		//tax validation
		if($("#tax").val().trim() == ""){
			return "Insert tax percentage...";
		}
		
		var temp5 = $("#tax").val().trim();
		
		//if(!$.isNumeric(temp5)){
			//return "Incorrect..."
		//}
		
		$("#tax").val(parseFloat(temp5).toFixed(2));


		return true;

	}

	function onPaymentSchemeSaveComplete(response, status){
		
		
		
		var resultSet = JSON.parse(response);
		
		console.log("ok");
		if(resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Succesfully Inserted...");
			$("#alertSuccess").show();		
			
			$("#divPSGrid").html(resultSet.data);
			$("#hidPSIDSave").val("");
			$("#ps-form")[0].reset();
	
		}
		
		else if(resultSet.status.trim() == "error"){
			

			 $("#alertError").text(resultSet.data);
			 $("#alertError").show(); 
			
		} 
		
		else if (status == "error")
		{
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
		} 
		else
		 {
			 $("#alertError").text("Unknown error while saving..");
			 $("#alertError").show();
		} 
			
		
	}

	$(document).on("click", ".btnUpdate", function(event){
		
		console.log("update pressed");
		
		$("#hidPSIDSave").val($(this).closest('tr').find("#hidItemIDUpdate").val());
		
		$("#docID").val($(this).closest("tr").find('td:eq(1)').text());
		$("#hospitalID").val($(this).closest("tr").find('td:eq(2)').text());
		$("#docCharge").val($(this).closest("tr").find('td:eq(3)').text());
		$("#hospCharge").val($(this).closest("tr").find('td:eq(4)').text());
		$("#tax").val($(this).closest("tr").find('td:eq(5)').text());
		
	});


	$(document).on("click", ".btnRemove", function(event){
		
		console.log("Delete Pressed")
		$.ajax({
			
			url : "PaymentSchemeAPI",
			type : "DELETE",
			data : "itemID=" + $(this).data("itemid"),
			dataType : "text",
			complete : function(response, status){
				
				onPSDeleteComplete(response.responseText, status);
		
			}
			
			
			
		});
		
	});


	function onPSDeleteComplete(response, status){
		console.log("response::::::"+JSON.stringify(response));
		console.log("status:::::"+status)		
	
		if (status == "success"){
			
			var resultSet = JSON.parse(response);
	
			 if (resultSet.status.trim() == "success"){
				 
		
				 $("#alertSuccess").text("Successfully deleted.");
			 
				 $("#alertSuccess").show();
			
				 $("#divPSGrid").html(resultSet.data);
			 } else if (resultSet.status.trim() == "error"){
				 
			 
				 $("#alertError").text(resultSet.data);
			 
				 $("#alertError").show();
			 
			 } }else if (status == "error"){
				 
			 
				 $("#alertError").text("Error while deleting.");
			 
				 $("#alertError").show();
			 } else {
				 $("#alertError").text("Unknown error while deleting..");
			 	$("#alertError").show();
			 }
	}


	
	</script>

</body>

</html>