package com.wotung.integration.member.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 田圃森 on 2019/2/11.
 */
@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
// 换成下面的方法有问题
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    ElapseHandlerInterceptor elapseHandlerInterceptor;
    @Autowired
    ParameterInterceptor parameterInterceptor;

//    // 跨域问题　https://www.cnblogs.com/yuansc/p/9076604.html
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义记录日志的拦截器 调用按顺序
        registry.addInterceptor(elapseHandlerInterceptor);
        registry.addInterceptor(parameterInterceptor);
    }
}
