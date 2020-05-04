$(document).ready(function(){
	
	$("#alertSuccess").hide();
	$("#alertError").hide();
	
});

//=======================SAVE==================
$(document).on("click", "btnSave", function(event){
	
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
	if(!$.inNumeric(temp4)){
		return "Incorrect..."
	}
	
	$("#hospCharge").val(parseFloat(temp4).toFixed(2));
	
	
	//tax validation
	if($("#tax").val().trim() == ""){
		return "Insert tax percentage...";
	}
	
	var temp5 = $("#tax").val().trim();
	
	if(!$.isNumeri(temp5)){
		return "Incorrect..."
	}
	
	$("#tax").val(parseFloat(temp5).toFixed(2));


	return true;

}

