package com.kaishengit.web.emp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.kaishengit.entity.Company;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;


@WebServlet("/emp/add")
public class EmployeeAddServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmployeeService empService = new EmployeeService();
	CompanyService comService = new CompanyService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Company> comList = comService.getCompanyList();
		List<String> deptNameList = comService.getDeptNameList();
 		
		
		req.setAttribute("comList", comList);
		req.setAttribute("deptList", deptNameList);
		forward("emp/employee_add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取表单数据
		String userName = req.getParameter("userName");
		String idNo = req.getParameter("idNo");
		String tel = req.getParameter("tel");
		String companyId = req.getParameter("company");
		String deptName = req.getParameter("dept");

		Map<String,Object> result = Maps.newHashMap();
		try{
			empService.saveEmployee(userName, idNo, tel, deptName, companyId);
			
			//result.put("state", "success");
			
		}catch(ServiceException e) {
			//新增失败，请求转发到增加页面，向增加页面传值
			result.put("state", "error");
			result.put("message", e.getMessage());
		}
		sendJson(result,resp);
	}
	
}
