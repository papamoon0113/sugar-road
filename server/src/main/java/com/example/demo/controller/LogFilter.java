package com.example.demo.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();

        System.out.println("---Request(" + requestURI + ") 필터---");
        chain.doFilter(request, response);
        System.out.println("---Request(" + requestURI + ") 필터---");
    }

    @Override
    public void destroy(){
        System.out.println("---필터 인스턴스 종료---");
    }
}
