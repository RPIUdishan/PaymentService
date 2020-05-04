package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.PaymentSchemeBean;
import util.DBConnectionUtil;

/**
 * 
 * @author Ishanka
 * Payment scheme class
 * here implemented all the operations of  PaymentScheme
 *
 */
public class PaymentScheme {
	
	
	DBConnectionUtil dbCon = new DBConnectionUtil(); // DB connection object for all methods
	
	
	//--------------------------------view all the payment schemes-------------------------------
	public String viewAllPaymentSchemes() {
		
		String output = "";
		
		PaymentSchemeBean psBean = new PaymentSchemeBean();
		
		try {
			
			Connection con = dbCon.connect();
			
			if (con == null) { return "Error While connection to database"; }
			
			//html table output headings
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Doctor ID</th>"
					+ "<th>Hospital ID</th>"
					+ "<th>Doctor's Charge</th>"
					+ "<th>Hospital's Charge</th>"
					+ "<th>Tax</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>"; 
			
			
			String query = "SELECT * FROM payment_schemes";
			
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {

					psBean.setId(rs.getInt("id"));
					psBean.setDoc_id(rs.getInt("doc_id"));
					psBean.setHospital_id(rs.getInt("hospital_id"));
					psBean.setDoc_charge(rs.getDouble("doc_charge"));
					psBean.setHosp_charge(rs.getDouble("hosp_charge"));
					psBean.setTax(rs.getDouble("tax"));

					output += "<tr><td>"+psBean.getId()+" </td>";
					output += "<td>"+psBean.getDoc_id()+"</td>";
					output += "<td>"+psBean.getHospital_id()+"</td>";
					output += "<td>"+psBean.getDoc_charge()+"</td>";
					output += "<td>"+psBean.getHosp_charge()+"</td>";
					output += "<td>"+psBean.getTax()+"</td>";
					
					
					//buttons in a row
					output += "<td><input name='btnUpdate' type='button' value='update' class='btn btn-outline-success'></td>"
							+ "<td><input name='btnRemove' type='button' value='remove' class='btn btn-outline-danger'></td>"		;
					
			}
			
		} catch (Exception e) {
			
			 output = "Error while reading the payment schemes.";
			 System.err.println(e.getMessage()); 
			
		}
		
		return output;
		
		
	}
	
	//-------------------------------------insert a payment scheme--------------------------------
	public String insertPaymentScheme(PaymentSchemeBean psBean) {
		
		String output = "";
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error while connecting to the database for inserting.";}
			
			String query = "insert into payment_schemes(id, doc_id, hospital_id, doc_charge, hosp_charge, tax)"
					+ "values(?, ?, ?, ?, ?, ?)"; 
			
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			//value binding
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, psBean.getDoc_id());
			preparedStatement.setInt(3, psBean.getHospital_id());
			preparedStatement.setDouble(4, psBean.getDoc_charge());
			preparedStatement.setDouble(5, psBean.getHosp_charge());
			preparedStatement.setDouble(6, psBean.getTax()); // changed
			
			preparedStatement.execute();
			con.close();
			
			output = "Inserted successfully"; 
			
		} catch (Exception e) {
		
			 output = "Error while inserting the Scheme.";
			 System.err.println(e.getMessage()); 
			 
		}
		
		
		return output;
		
	}
	
	//------------------------------delete a payment scheme----------------------------------
	public String deletePayementScheme(PaymentSchemeBean psBean){
	
		String output = "";
		
		try{
			
		Connection con = dbCon.connect();
		
		if(con == null) { System.out.println("Error while connecting to the database for deleting."); }

		
		String query = "DELETE FROM payment_schemes WHERE id = ?";
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		
		preparedStatement.setInt(1, psBean.getId());
		
		preparedStatement.execute();
		
		con.close();
		
		output = "Succefully Deleted, Payment Scheme ID : " +psBean.getId();
				
		}
		catch (Exception e) {

			System.out.println("Error while deleting");
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//------------------------------------ update the payment scheme data-------------------------------------------------------
	public String updatePaymentScheme(PaymentSchemeBean psBean) {
		
		String output = "";
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error while connecting to the database for updating";}
			
			String query = "UPDATE payment_schemes SET  doc_id=?, hospital_id=?, doc_charge=?, hosp_charge=?, tax=?"
					+ "WHERE id = ?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, psBean.getDoc_id());
			preparedStatement.setInt(2, psBean.getHospital_id());			
			preparedStatement.setDouble(3, psBean.getDoc_charge());
			preparedStatement.setDouble(4, psBean.getHosp_charge());
			preparedStatement.setDouble(5, psBean.getTax());
			preparedStatement.setInt(6, psBean.getId());
			
			
		preparedStatement.execute();
		con.close();
		
		output = "Update Succesfully";
			
		} catch (Exception e) {
			
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
}
