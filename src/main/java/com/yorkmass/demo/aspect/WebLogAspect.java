package com.yorkmass.demo.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

@Aspect
@Component //配置bean
public class WebLogAspect {
    private Logger logger=Logger.getLogger(getClass());

    //execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)  除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。
    //#匹配定义在com.yorkmass.demo.controller包或者子包里的任意public方法的执行：如下。第一个*表示返回类型为任意。com.yorkmass.demo.controller..*.*表示返回包或子包的任意方法名。最后(..)返回任意参数
    @Pointcut("execution(public * com.yorkmass.demo.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        logger.info("-------------------requerst--------------------");
        logger.info("URL:"+request.getRequestURL().toString());
        logger.info("HTTP_METHOD:"+request.getMethod());
        logger.info("IP:"+request.getRemoteAddr());
        Enumeration<String> enu=request.getParameterNames();
        while (enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            logger.info("name:"+name+"--value:"+request.getParameter(name));
        }
    }
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        logger.info("-------------response--------------");
        //处理完请求，应答
        logger.info("RESPONSE:"+ret);
    }


}
