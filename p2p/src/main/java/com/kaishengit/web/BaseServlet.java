package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kaishengit.entity.Employee;

public class BaseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void forward(String path,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(req, resp);
	}
	
	public void sendJson(Object obj,HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String json = new Gson().toJson(obj);
		out.print(json);
		out.flush();
		out.close();
		
	}
	
	public void sendText(Object obj,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}
	
	public Employee getSessionEmployee(HttpServletRequest req){
		//从session中获取当前登录者
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("emp");
		return emp;
				
	}
	
	
	
}
