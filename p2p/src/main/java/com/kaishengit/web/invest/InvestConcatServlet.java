package com.kaishengit.web.invest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Invest;
import com.kaishengit.entity.Project;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.InvestService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/invest/concat")
public class InvestConcatServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvestService investService = new InvestService();
	CustomerService customerService = new CustomerService();
	ProjectService proService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Map<String,Object> maps = Maps.newHashMap();
		
		if(StringUtils.isNumeric(id)) {
			Invest invest = investService.findInvestById(id);
			Customer cust = customerService.findCustById(invest.getCustId());
			Project pro = proService.findProjectById(String.valueOf(invest.getProId()));
			maps.put("state", "success");
			maps.put("invest", invest);
			maps.put("cust", cust);
			maps.put("pro", pro);
		} else{
			maps.put("state", "error");
			maps.put("message", "参数异常");
		}
		
		sendJson(maps,resp);
	}
	
}
