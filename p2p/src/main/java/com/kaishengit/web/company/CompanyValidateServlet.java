package com.kaishengit.web.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.CompanyService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/com/validate")
public class CompanyValidateServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CompanyService comService = new CompanyService();

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String companyName = request.getParameter("compName");
		companyName = new String(companyName.getBytes("ISO-8859-1"),"UTF-8");
		boolean result = comService.validateComopanyName(companyName);
		sendText(result, resp);
		
	}

}
