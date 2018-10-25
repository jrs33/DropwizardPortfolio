package com.site.joshsurette.core.servlets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public final class CorsFilter implements Filter {

    private static final String ACCESS_CONTROL_ORIGIN  = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_METHODS = "Access-Control-Allow-Methods";

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader(ACCESS_CONTROL_ORIGIN, "*");
        response.setHeader(ACCESS_CONTROL_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
    }

    @Override
    public void destroy() {}
}
