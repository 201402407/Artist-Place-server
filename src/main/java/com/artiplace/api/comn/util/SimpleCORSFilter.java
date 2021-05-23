package com.artiplace.api.comn.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {

public SimpleCORSFilter() {
    log.info("SimpleCORSFilter init");
}

@Override
@Order(Ordered.HIGHEST_PRECEDENCE)
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    if(request.getHeader("Origin") == null) {
    	System.out.println("NULL");
    }
    else {
    	System.out.println(request.getHeader("Origin"));
    }
//    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3660");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
//    response.setHeader("Access-Control-Allow-Headers", "*");

//    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//        response.setStatus(HttpServletResponse.SC_OK);
//    } else {
//        chain.doFilter(req, res);
//    }
    chain.doFilter(req, res);	
}

@Override
public void init(FilterConfig filterConfig) {
}

@Override
public void destroy() {
}

}
