package com.kaishengit.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Project;
import com.kaishengit.service.CompanyService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/pro/list")
public class ProjectListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("page");
		Integer pageNo = StringUtils.isNumeric(p)?Integer.parseInt(p):1;
		
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		if(StringUtils.isNotEmpty(value)) {
			value = StringUtils.isoToUtf(value);
		}
		
		ProjectService proService = new ProjectService();
		Page<Project> page = proService.getProjectList(pageNo,key,value);
		req.setAttribute("page", page);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward("project/project_list",req,resp);
	}


}
