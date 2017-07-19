package com.kaishengit.crm.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/7/19.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        if (url.equals("/")) {
            return true;
        } else {
            if (request.getSession().getAttribute("acc") != null) {
                return true;
            }
        }
         response.sendRedirect("/?callback="+url);
        return false;

    }
}
