package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.Page;

@WebServlet("/emp/list")
public class EmployeeListServlet extends HttpServlet{

	EmployeeService empService = new EmployeeService();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		if(StringUtils.isNotEmpty(value)) {
			value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		String page = req.getParameter("page");
		int pageNo = 1;
		
		if(StringUtils.isNumeric(page)){
			pageNo = Integer.parseInt(page);
		}
		
		Page<Employee> resultPage = empService.findEmployeeByParams(key,value,pageNo);
		req.setAttribute("page", resultPage);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.getRequestDispatcher("/WEB-INF/views/emp/employee_list.jsp").forward(req, resp);
	}
	
}
