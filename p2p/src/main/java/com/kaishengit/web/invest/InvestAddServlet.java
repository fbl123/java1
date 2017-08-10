package com.kaishengit.web.invest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.InvestService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/invest/add")
public class InvestAddServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CustomerService custService = new CustomerService();
	ProjectService proService = new ProjectService();
	InvestService investService = new InvestService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//如果是从项目列表页面过来，需要获得传来的proId（项目id），并且已默认值的形式展示在页面
		String proId = req.getParameter("proId");
		//获取客户的列表
		List<Customer> custList = custService.findAllCustomer();
		//获取项目列表
		List<Project> proList = proService.findAllProject();
		
		req.setAttribute("proId", proId);
		req.setAttribute("custList", custList);
		req.setAttribute("proList", proList);
		forward("invest/invest_add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("cust");
		String proId = req.getParameter("pro");
		String remainMoney = req.getParameter("remainMoney");
		String investMoney = req.getParameter("investMoney");
		String rate = req.getParameter("rate");
		String month = req.getParameter("month");
		String signDate = req.getParameter("signDate");
		String endDate = req.getParameter("endDate");
		
		//获得登录者Id
		int empId = getSessionEmployee(req).getId();
		investService.addInvest(custId,proId,empId,remainMoney,investMoney,rate,month,signDate,endDate);
		Result res = new Result("success");
		sendJson(res, resp);
	}
	
	
}
