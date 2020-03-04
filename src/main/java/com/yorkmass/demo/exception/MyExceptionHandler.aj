package com.yorkmass.demo.exception;

import com.yorkmass.demo.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){

        if(e instanceof MyException){
            MyException myException =  (MyException)e;
            return JsonData.buildError(myException.getMsg(),myException.getCode());
        }else{
            return JsonData.buildError("全局异常，未知错误");
        }
    }


}