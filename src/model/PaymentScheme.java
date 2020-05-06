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
			output = "<table border='1'>"
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

					
					output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value="+psBean.getId()+'>'+psBean.getId()+"</td>";
					output += "<td>"+psBean.getDoc_id()+"</td>";
					output += "<td>"+psBean.getHospital_id()+"</td>";
					output += "<td>"+psBean.getDoc_charge()+"</td>";
					output += "<td>"+psBean.getHosp_charge()+"</td>";
					output += "<td>"+psBean.getTax()+"</td>";
					
					
					//buttons in a row
					output += "<td><input id='btnUpdate' name='btnUpdate' , type='button' value='update' class='btnUpdate btn btn-outline-success'></td>"
							+ "<td><input id='btnRemove' name='btnRemove' , type='button' value='remove' class='btnRemove btn btn-outline-danger' data-itemid = '" +psBean.getId()+"'>"+"</td>";
					
			}
			
			  con.close();
			
			  output += "</table>"; 
			
		} catch (Exception e) {
			
			 output = "Error while reading the payment schemes.";
			 System.err.println(e.getMessage()); 
			
		}
		
		return output;
		
		
	}
	
	//-------------------------------------insert a payment scheme--------------------------------
	public String insertPaymentScheme(String docID, String hospitalID, String docCharge, String hospCharge, String tax) {
		String output = "";
		
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error while connecting to the database for inserting.";}
			
			String query = "insert into payment_schemes(id,doc_id,hospital_id, doc_charge, hosp_charge, tax) values(?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, Integer.parseInt(docID));
			preparedStatement.setInt(3, Integer.parseInt(hospitalID));
			preparedStatement.setDouble(4, Double.parseDouble(docCharge));
			preparedStatement.setDouble(5, Double.parseDouble(hospCharge));
			preparedStatement.setDouble(6, Double.parseDouble(tax));
			
			preparedStatement.execute();
			con.close();
			
			output = "Inserted successfully"; 
			
			
			String newPaymentScheme = viewAllPaymentSchemes();
			output = "{\"status\":\"success\", \"data\": \"" +newPaymentScheme + "\"}"; 
		} catch (Exception e) {
		
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 
		}
		
		
		return output;
		
	}
	
	//------------------------------delete a payment scheme----------------------------------
	public String deletePayementScheme(String id){
	
		String output = "";
			
		try{
			
		Connection con = dbCon.connect();
		
		if(con == null) { System.out.println("Error while connecting to the database for deleting."); }

		
		String query = "DELETE FROM payment_schemes WHERE id = ?";
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		
		preparedStatement.setInt(1, Integer.parseInt(id));
		
		preparedStatement.execute();
		
		
		
		con.close();
		
		String newPaymentScheme = viewAllPaymentSchemes();
		
		output = "{\"status\":\"success\", \"data\": \"" +newPaymentScheme + "\"}"; 
				
		}
		catch (Exception e) {

			System.out.println("Error while deleting");
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//------------------------------------ update the payment scheme data-------------------------------------------------------
	public String updatePaymentScheme(String id,String docID, String hospitalID, String docCharge, String hospCharge, String tax) {
		
		String output = "";
	
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error while connecting to the database for updating";}
			
			String query = "UPDATE payment_schemes SET  doc_id=?, hospital_id=?, doc_charge=?, hosp_charge=?, tax=?"
					+ "WHERE id = ?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, Integer.parseInt(docID));
			preparedStatement.setInt(2, Integer.parseInt(hospitalID));
			preparedStatement.setDouble(3, Double.parseDouble(docCharge));
			preparedStatement.setDouble(4, Double.parseDouble(hospCharge));
			preparedStatement.setDouble(5, Double.parseDouble(tax));
			preparedStatement.setDouble(6, Double.parseDouble(id));
			
		preparedStatement.execute();
		con.close();
		
		
		String newPS = viewAllPaymentSchemes();
		output = "{\"status\":\"success\", \"data\": \"" +newPS + "\"}"; 
		
			
		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
}
