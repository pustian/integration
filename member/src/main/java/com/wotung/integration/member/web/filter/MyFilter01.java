package com.wotung.integration.member.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/")
public class MyFilter01 implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("enter MyFilter01 ===");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("=== exit MyFilter01 ");
    }

    @Override
    public void destroy() {

    }
}
