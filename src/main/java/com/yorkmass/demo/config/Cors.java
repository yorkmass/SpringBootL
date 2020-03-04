package com.yorkmass.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域配置 注意点：假如接口报错，则跨域配置不生效
 */
@Configuration
public class Cors extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE","PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
