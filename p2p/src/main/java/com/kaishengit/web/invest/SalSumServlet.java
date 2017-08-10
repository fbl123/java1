package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.SalarySum;
import com.kaishengit.service.SalaryService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/sal/sum")
public class SalSumServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SalaryService salService = new SalaryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String p = req.getParameter("p");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)){
			pageNo = Integer.parseInt(p);
		}
	
		value = StringUtils.isoToUtf(value);
		
		Page<SalarySum> page = salService.findBySalSum(pageNo,key,value);
		req.setAttribute("page", page);
		forward("sal/sal_sum", req, resp);
		
	}
}
