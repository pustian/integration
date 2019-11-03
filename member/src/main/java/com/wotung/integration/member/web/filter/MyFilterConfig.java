package com.wotung.integration.member.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean builderAFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(15);
        filterRegistrationBean.setFilter(new MyFilterA());
        filterRegistrationBean.setName("filterA--order15");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean builderBFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(5);
        filterRegistrationBean.setFilter(new MyFilterB());
        filterRegistrationBean.setName("filterB--order5");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean builderCFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(10);
        filterRegistrationBean.setFilter(new MyFilterC());
        filterRegistrationBean.setName("filterC--order10");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}

class MyFilterA implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("enter MyFilterA ===");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("=== exit MyFilterA ");
    }

    @Override
    public void destroy() {

    }
}


class MyFilterB implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("enter MyFilterB ===");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("=== exit MyFilterB ");
    }

    @Override
    public void destroy() {

    }
}


class MyFilterC implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("enter MyFilterC ===");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("=== exit MyFilterC ");
    }

    @Override
    public void destroy() {

    }
}