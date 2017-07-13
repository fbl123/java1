package com.kaishengit.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/13.
 */
public abstract class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
