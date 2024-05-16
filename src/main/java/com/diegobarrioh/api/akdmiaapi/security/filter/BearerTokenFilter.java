package com.diegobarrioh.api.akdmiaapi.security.filter;

import com.diegobarrioh.api.akdmiaapi.security.autentication.ApiKeyAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Log4j2
public class BearerTokenFilter extends OncePerRequestFilter {

    public static final String HEADER_NAME = "authentication";
    public static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //0 should we execute
        if (!Collections.list(request.getHeaderNames()).contains(HEADER_NAME)) {
            log.info("there's no " + HEADER_NAME + " header, moving on....");
            filterChain.doFilter(request, response);
            return;
        }


        String auth = request.getHeader(HEADER_NAME);
        if (!auth.startsWith(BEARER)) {
            log.info("there's no Bearer [token], moving on....");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
            response.getWriter().write("not bearer token, moving on....");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("found the token {}", auth.substring(BEARER.length()));
        //We create an authentication and put it in the security context.
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(new ApiKeyAuthentication());
        SecurityContextHolder.setContext(newContext);
        filterChain.doFilter(request, response);
        return;
    }
}
