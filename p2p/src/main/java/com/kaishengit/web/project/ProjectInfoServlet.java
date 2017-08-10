package com.kaishengit.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/pro/info")
public class ProjectInfoServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProjectService proService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String proId = req.getParameter("id");
		Result res = null;
		if (StringUtils.isNumeric(proId)) {
			Project pro = proService.findProjectById(proId);
			res = new Result("success");
			res.setData(pro);
		} else {
			res = new Result("error", "参数错误");
		}
		sendJson(res, resp);
	}

}
