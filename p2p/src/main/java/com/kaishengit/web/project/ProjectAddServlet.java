package com.kaishengit.web.project;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.BaseServlet;


@WebServlet("/pro/add")
public class ProjectAddServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProjectService proService = new ProjectService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("project/project_add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取表单数据
		String projectName = req.getParameter("projectName");
		String sumMoney = req.getParameter("sumMoney");
		String repayrate = req.getParameter("repayrate");
		String months = req.getParameter("months");
		String beginDate = req.getParameter("beginDate");
		String endDate = req.getParameter("endDate");
		String remark = req.getParameter("remark");
		
		Map<String,Object> result = Maps.newHashMap();
		try{
			proService.saveProject(projectName, sumMoney, repayrate, months, beginDate,endDate,remark);			
			result.put("state", "success");
			
		}catch(ServiceException e) {
			result.put("state", "error");
			result.put("message", e.getMessage());
		}
		sendJson(result,resp);
	}
	
}
