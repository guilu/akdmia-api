package com.diegobarrioh.api.akdmiaapi.security.xss;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class XSSFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }
}
