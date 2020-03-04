package com.yorkmass.demo.exception;

import com.yorkmass.demo.interceoter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录拦截器配置
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/stu/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
