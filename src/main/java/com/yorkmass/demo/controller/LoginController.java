package com.yorkmass.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpResponse;
import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.service.UserService;
import com.yorkmass.demo.service.impl.UserServiceImp;
import com.yorkmass.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
//所有对象转换为json对象返回给客户端
@RestController
public class LoginController {
    @Autowired
    private UserService service;
    @RequestMapping("/login")
    public Object login(@RequestParam(value = "username") String username, @RequestParam(value = "password")String password,HttpServletResponse response) throws Exception {
        Map<String,String> map=new HashMap<>();
        User user=new User();
        if(service.loginCheck(username,password)){
            user.setPassword(password);
            user.setUsername(username);
            String token=JwtUtils.geneJsonWebToken(user);
            Cookie cookie=new Cookie("token",token);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            map.put("status","success");
        }else {
            map.put("status","账号密码有误！");
        }
        return map;
    }

}
