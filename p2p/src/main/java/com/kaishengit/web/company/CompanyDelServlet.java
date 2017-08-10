package com.kaishengit.web.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/com/del")
public class CompanyDelServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CompanyService comService = new CompanyService();

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Result res = null;
		if(StringUtils.isNumeric(id)){
			try{
				comService.delCompanyById(Integer.parseInt(id));
				res = new Result("success");
			}catch(ServiceException e) {
				res = new Result("error",e.getMessage());
			}
		} else{
			res = new Result("error","参数异常");
		}
		sendJson(res,resp);
		
	}

}
