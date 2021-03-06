package com.yorkmass.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpResponse;
import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.service.UserService;
import com.yorkmass.demo.service.impl.UserServiceImp;
import com.yorkmass.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
//所有对象转换为json对象返回给客户端
@RestController
public class LoginController {
    @Autowired
    private UserService service;

    /*
    登录
     */
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
    /*
    更新用户信息
     */
    @RequestMapping("/submitinfo")
    public Object submitInfo(HttpServletRequest request) throws ParseException {
        String token=service.getTokenFromClient(request);
        String username=service.getUsernameByToken(token);
        User user=new User();
        user.setName(request.getParameter("name"));
        user.setGender(request.getParameter("gender").equals("男")?1:0);
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlbirth = new java.sql.Date(sdf.parse(request.getParameter("birth")).getTime());
        user.setBirth(sqlbirth);
        user.setUsername(username);
        Integer a=service.setUserInfo(user);
        System.out.println("更新成功"+a);
        Map<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }
    /*
    修改密码,原始密码是之前从token里面取出比较的，所以修改密码之后需要重新登录     */
    @RequestMapping("/changepwd")
    public Object changePwd(HttpServletRequest request){
        String token=service.getTokenFromClient(request);
        String username=service.getUsernameByToken(token);
        System.out.println(request.getParameter("newPwd"));
        service.changePwd(request.getParameter("newPwd"),username);
        Map<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

}
