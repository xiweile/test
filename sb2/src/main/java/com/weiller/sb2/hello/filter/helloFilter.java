package com.weiller.sb2.hello.filter;

import javax.servlet.*;
import java.io.IOException;

public class helloFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("hello filter before");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("hello filter after");
    }

    @Override
    public void destroy() {

    }
}
