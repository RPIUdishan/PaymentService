package com;

import java.io.IOException;

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
		
		psBean.setDoc_id(Integer.parseInt(req.getParameter("doc_id")));	
		psBean.setHospital_id(Integer.parseInt(req.getParameter("hospitalID")));
		psBean.setDoc_charge(Double.parseDouble(req.getParameter("docCharge")));
		psBean.setHosp_charge(Double.parseDouble(req.getParameter("hospCharge")));
		psBean.setTax(Double.parseDouble(req.getParameter("tax")));
		
		String output = ps.insertPaymentScheme(psBean);
		
		resp.getWriter().write(output); 
		
	}
}