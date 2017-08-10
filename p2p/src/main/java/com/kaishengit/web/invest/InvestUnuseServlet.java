package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.InvestService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/invest/unuse")
public class InvestUnuseServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvestService investService = new InvestService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Result res = null;

		if (StringUtils.isNumeric(id)) {
			try {
				investService.unuseInvestById(id);
				res = new Result("success");
			} catch (ServiceException e) {
				res = new Result("error", e.getMessage());
			}

		} else {
			res = new Result("error", "参数异常");

		}
		sendJson(res, resp);

	}

}
