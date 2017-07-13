package com.kaishengit.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/13.
 */
@WebFilter("/*")
public  class EncodingFilter extends MyFilter {
    private String encoding="utf-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(!StringUtils.isEmpty(filterConfig.getInitParameter("encoding"))){
            this.encoding=filterConfig.getInitParameter("encoding");
        }
    }
}
