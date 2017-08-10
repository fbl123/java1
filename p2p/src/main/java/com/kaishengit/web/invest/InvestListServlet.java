package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Invest;
import com.kaishengit.service.InvestService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/invest/list")
public class InvestListServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvestService investService = new InvestService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("page");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)){
			pageNo = Integer.parseInt(p);
		}
		
		//TODO 添加转码
		
		value = StringUtils.isoToUtf(value);
		
		Page<Invest> page = investService.queryByparam(pageNo,key,value);
		
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", page);
		forward("invest/invest_list", req, resp);
		
	}
	
}
