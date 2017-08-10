package com.kaishengit.web.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/emp/edit")
public class EmployeeEditServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EmployeeService empService = new EmployeeService();
	CompanyService comService = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (StringUtils.isNumeric(id)) {
			Employee emp = empService.findEmployeeById(Integer.parseInt(id));
			if(emp == null) {
				resp.sendError(404, "参数异常");
			} else{
				List<Company> comList = comService.getCompanyList();
				List<String> deptNameList = comService.getDeptNameList();
		 		
				
				req.setAttribute("comList", comList);
				req.setAttribute("deptList", deptNameList);
				req.setAttribute("emp", emp);
				
				forward("emp/employee_edit", req, resp);
			}
		} else {
			resp.sendError(404, "参数异常");
		}

		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		String companyId = req.getParameter("company");
		String deptName = req.getParameter("dept");

		EmployeeService empService = new EmployeeService();
		Result result = null;
		
		try{
			empService.editEmployee(id,state,companyId,deptName);
			result = new Result("success");
		} catch(ServiceException e) {
			result = new Result("error",e.getMessage());
		}
		
		sendJson(result,resp);
	}
	
}
