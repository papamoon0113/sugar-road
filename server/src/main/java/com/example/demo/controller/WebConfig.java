package com.example.demo.controller;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<Filter> logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LogFilter()); //내가 구현한 필터 넣기
        filterRegistrationBean.setOrder(1); //필터 체인할 때 가장 먼저 실행
        filterRegistrationBean.addUrlPatterns("/*"); //모든 요청 url에 대해 실행
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoginCheckFilter()); //내가 구현한 필터 넣기
        filterRegistrationBean.setOrder(2); //필터 체인할 때 가장 먼저 실행
        filterRegistrationBean.addUrlPatterns("/*"); //모든 요청 url에 대해 실행
        return filterRegistrationBean;
    }
}
