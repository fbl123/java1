package com.kaishengit.web.cust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/cust/birth/list")
public class CustomerBirthRemindServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CustomerService custService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String days = req.getParameter("days");
		
		days = days == null ? "1" : days;
		
		String page = req.getParameter("page");
		int pageNo = 1;
		
		if(StringUtils.isNumeric(page)){
			pageNo = Integer.parseInt(page);
		}
		
		Page<Customer> custPage = custService.findCustomerByQueryParam(Integer.parseInt(days),pageNo);
		req.setAttribute("page", custPage);
		forward("cust/cust_birth", req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
