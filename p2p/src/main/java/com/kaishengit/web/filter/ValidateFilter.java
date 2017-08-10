package com.kaishengit.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.AESUtils;

public class ValidateFilter extends AbstractFilter {

	List<String> lists = null;
	EmployeeService empService = new EmployeeService();

	@Override
	public void init(FilterConfig config) throws ServletException {
		String validateUrl = config.getInitParameter("validateUrl");
		lists = Arrays.asList(validateUrl.split(","));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		// 获取uri，判断uri是否直接通过
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		// 1.访问的uri是否需要校验
		if (validate(uri)) {
			// /static/js/emp/login.js
			String regex = "(/{1}\\w+)+";
			if (uri.matches(regex)) {
				// 2.session中是否存在emp对象
				HttpSession session = req.getSession();
				Employee emp = (Employee) session.getAttribute("emp");
				if (emp == null) {
					// 3.实现自动登录
					emp = getEmployeeByCookie(req);
					if (emp != null) {
						session.setAttribute("emp", emp);
						filterChain.doFilter(req, resp);
					} else {
						// TODO uri参数列表
						resp.sendRedirect("/login?callback=" + uri);
					}
				} else {
					filterChain.doFilter(req, resp);
				}
			} else {
				resp.sendError(404, "url不正确");
			}
		} else {
			filterChain.doFilter(req, resp);
		}

	}

	/**
	 * 从cookie中获取userName、password进行自动登录
	 * 
	 * @param req
	 * @return
	 */
	private Employee getEmployeeByCookie(HttpServletRequest req) {
		Employee emp = null;
		// 获得Cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userToken")) {
					String userToken = cookie.getValue();
					String userPass = AESUtils.decode(userToken);
					String userName = userPass.split("-")[0];
					String password = userPass.split("-")[1];
					// 获得当前登录的IP
					String ip = req.getRemoteAddr();
					emp = empService.login(userName, password, ip);

				}
			}
		}

		return emp;
	}

	/**
	 * 校验uri是否需要验证
	 * 
	 * @param uri
	 * @return
	 */
	private boolean validate(String uri) {
		if (lists == null) {
			return false;
		}

		if (lists.contains(uri)) {
			return true;
		}

		for (String str : lists) {
			// /emp /invest...
			// //emp/list emplist empadd emp
			if (uri.startsWith(str)) {
				return true;
			}
		}

		return false;
	}

}
