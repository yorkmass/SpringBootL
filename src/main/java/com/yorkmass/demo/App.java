package com.yorkmass.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.yorkmass.demo.controller","com.yorkmass.demo.exception","com.yorkmass.demo.datasource","com.yorkmass.demo.test1.service","com.yorkmass.demo.test2.service"})
@ComponentScan(basePackages = {"com.yorkmass.demo.config","com.yorkmass.demo.controller","com.yorkmass.demo.service","com.yorkmass.demo.aspect","com.yorkmass.demo.exception"})
@MapperScan(basePackages ="com.yorkmass.demo.mapper")
@ServletComponentScan
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
