package com.kaishengit.web.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/com/add")
public class CompanyAddServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CompanyService comService = new CompanyService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> cities = comService.getCity();
		req.setAttribute("cityList", cities);
		forward("company/company_add", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String companyName = req.getParameter("compName");
		String phone = req.getParameter("phone");
		String city = req.getParameter("city");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");

		Map<String, String> result = new HashMap<>();
		try {
			comService.addCompany(companyName, phone, city, address, remark);
			result.put("state", "success");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.put("state", "error");
			result.put("message", "新增失败，请稍后再试");
		}
		sendJson(result, resp);
	}

}
