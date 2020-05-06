package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import beans.PaymentSchemeBean;
import model.PaymentScheme;

@WebServlet("/PaymentSchemeAPI")
public class PaymentSchemeAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PaymentScheme ps = new PaymentScheme();
	PaymentSchemeBean psBean = new PaymentSchemeBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String output = ps.insertPaymentScheme(req.getParameter("docID"), 
				                               req.getParameter("hospitalID"), 
				                               req.getParameter("docCharge"), 
				                               req.getParameter("hospCharge"), 
				                               req.getParameter("tax"));
		
		System.out.println("output:::::"+output);
		
		resp.getWriter().write(output); 
		
	}
	
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map paras = getParasMap(req); 
		
		String output = ps.deletePayementScheme(paras.get("itemID").toString());
		
		resp.getWriter().write(output); 
	
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String output = ps.updatePaymentScheme(paras.get("hidPSIDSave").toString(),
				paras.get("docID").toString(), 
				paras.get("hospitalID").toString(), 
				paras.get("docCharge").toString(), 
				paras.get("hospCharge").toString(),
				paras.get("tax").toString());
		
		response.getWriter().write(output); 
		
	}
	
	
	private static Map getParasMap(HttpServletRequest request) {
		
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? 
					scanner.useDelimiter("\\A").next() : ""; 
			
					scanner.close(); 
					
					String[] params = queryString.split("&");
					 for (String param : params)
					 { String[] p = param.split("=");
					 map.put(p[0], p[1]);
					 } 
			
		} catch (Exception e) {
			
		}
		
		
		return map;
	}
}