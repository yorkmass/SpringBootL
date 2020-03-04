package com.yorkmass.demo.interceoter;

import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.yorkmass.demo.domain.JsonData;
import com.yorkmass.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {
    private static final Gson gson=new Gson();
    /**
     * 进入controller之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        if(token==null){
            token=request.getHeader("token");
        }
        if(token!=null){
            Claims claims=JwtUtils.checkJWT(token);
            if(claims!=null){
                String password=(String)claims.get("password");
                String username=(String)claims.get("username");
                request.setAttribute("password",password);
                request.setAttribute("username",username);
                return true;
            }
        }
        response.sendRedirect(request.getContextPath());
        return false;
    }

    /**
     * 响应数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response,Object obj) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer=response.getWriter();
        writer.print(gson.toJson(obj));
        writer.close();
        response.flushBuffer();
    }
}
