package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import util.DBConnectionUtil;

import beans.PaymentBean;


/**
 * 
 * @author Ishanka
 * all the operations are implemented here
 */
public class Payment {
	
	DBConnectionUtil dbCon = new DBConnectionUtil(); // database connection 
	
	//-----------------------view payment by its ID method-----------------------
	public String viewPaymentsOfPaymentById(int pid){
		
		String output = "";
		
		PaymentBean pBean = new PaymentBean(); // payment bean object
		
		try {
			
			Connection con = dbCon.connect(); //get db connection
			
			if(con == null) {return "Error While connection to database";}
			
			
			
			output = "<table border=\"1\">" // output shown as html table
					+ "<tr>"
					+ "<th>Payment ID</th>"
					+ "<th>Appoinment ID</th>"
					+ "<th>Payment Scheme ID</th>"
					+ "<th>Doctor ID</th>"
					+ "<th>Hospital ID</th>"
					+ "<th>Total Charge</th>"
					+ "</tr>";
			
			//query that return the values of a payment record according to its id
			String query = "select p.payment_id,p.appointment_id,p.paymentScheme_id,p.total_charge,ps.doc_id,ps.hospital_id from payment_tbl p  left outer join payment_schemes ps on p.paymentScheme_id=ps.id where p.payment_id="+pid;

			java.sql.Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				//getting values in Result set
				pBean.setPayment_id(rs.getInt("payment_id"));
				pBean.setAppointment_id(rs.getInt("appointment_id"));
				pBean.setPaymentScheme_id(rs.getInt("paymentScheme_id"));
				pBean.setTotal_charge(rs.getDouble("total_charge"));
				
				// set the output of values
				output += "<tr><td>"+pBean.getPayment_id()+" </td>";
				output += "<td>"+pBean.getPayment_id()+"</td>";
				output += "<td>"+pBean.getPaymentScheme_id()+"</td>";
				output += "<td>"+rs.getInt("doc_id") +"</td>";
				output += "<td>"+rs.getInt("hospital_id") +"</td>";
				output += "<td>"+pBean.getTotal_charge()+"</td>";
				
			}
			con.close(); // closing connection
		} catch (Exception e) {
			 output = "Error while reading the payments.";
			 System.err.println(e.getMessage()); 
		}
		
		
		return output;
		
	}  
	
	
	
	//---------------------------------total calculate according to the appointment details which are hospital id and doctor id-----------------------------------
	public void totalCal(int schedule_id,PaymentBean paymentBean ) {


		try {
			
			Connection con = dbCon.connect();
			
			if (con == null) { System.out.println("Error While reading from database"); }
			
				//query which is set the total of values 
			   	String query = "select p.id,((p.doc_charge+p.hosp_charge)*(tax+100)/100) as total FROM schedule s left outer join payment_schemes p on s.d_id=p.doc_id and s.h_id=p.hospital_id where schedule_id="+schedule_id;
			   	
				java.sql.Statement statement = con.createStatement();
				
				ResultSet rs = statement.executeQuery(query);
				
				while(rs.next()) {

					paymentBean.setTotal_charge(rs.getDouble("total"));
					paymentBean.setPaymentScheme_id(rs.getInt("id"));	
				}
				
				con.close();
				
	
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
	
	}

	
	//----------------insert a payment ------------------------
	public String insertPayment(PaymentBean pBean , int schedule_id ) {
		
		String output = "";
		
		totalCal(schedule_id, pBean); // call the totalCal method
	
		try {
			
			Connection con = dbCon.connect(); // db connection
			
			if(con == null) {return "Error while connecting to the database for inserting.";}
			
			
			String query = "insert into payment_tbl (appointment_id,paymentScheme_id,total_charge) values (?,?,?)"; // query of inserting
			
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			
			
			//value binding
			preparedStatement.setInt(1, pBean.getAppointment_id());
			preparedStatement.setInt(2, pBean.getPaymentScheme_id());
			preparedStatement.setDouble(3, pBean.getTotal_charge());
			
			preparedStatement.execute();
			con.close();
			
			output = "Inserted successfully"; // output result
			
		} catch (Exception e) {
		
			 output = "Error while inserting the Scheme.";
			 
			 System.err.println(e.getMessage()); 
			 
		}
		
		
		return output;
		
	}
	
	
	
	
	//------------------------------------view all payments method-------------------------------------------
	public String viewAllPayments() {
		
		String output = ""; // output initialization
		
		PaymentBean pBean = new PaymentBean();
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error While connection to database";}
			
			
			// html table output headings
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Appoinment ID</th>"
					+ "<th>Payment Scheme ID</th>"
					+ "<th>Total Charge</th>"
					+ "</tr>";
			
			String query = "SELECT * FROM payment_tbl"; // query of getting all the records

			java.sql.Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				pBean.setPayment_id(rs.getInt("payment_id"));
				pBean.setAppointment_id(rs.getInt("appointment_id"));
				pBean.setPaymentScheme_id(rs.getInt("paymentScheme_id"));
				pBean.setTotal_charge(rs.getDouble("total_charge"));
				
				output += "<tr><td>"+pBean.getPayment_id()+" </td>";
				output += "<td>"+pBean.getAppointment_id()+"</td>";
				output += "<td>"+pBean.getPaymentScheme_id()+"</td>";
				output += "<td>"+pBean.getTotal_charge()+"</td>";
			
			}
			
			con.close();
			
		} catch (Exception e) {
			
			 output = "Error while reading the payments.";
			 System.err.println(e.getMessage()); 
		}
		
		
		return output;
	}
	
			
	
}
