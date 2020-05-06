
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

