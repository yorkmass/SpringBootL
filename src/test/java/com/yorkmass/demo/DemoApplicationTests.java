package com.yorkmass.demo;

import com.google.gson.JsonObject;
import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.service.UserService;
import com.yorkmass.demo.service.impl.UserServiceImp;
import com.yorkmass.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@SpringBootTest
class DemoApplicationTests {
/**
    @Test
    void contextLoads() {
        TUser tUser=new TUser();
        tUser.setId(55);
        tUser.setUsername("yy");
        tUser.setPassword("mm");
        tUser.setEmpId("xx");
        String token= JwtUtils.geneJsonWebToken(tUser);
        System.out.println(token);
    }

    @Test
    void testCheck(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5b3JrbWFzcyIsImlkIjo1NSwidXNlcm5hbWUiOiJ5eSIsInBhc3N3b3JkIjoibW0iLCJlbXBJZCI6Inh4IiwiaWF0IjoxNTgyOTUzOTQ2LCJleHAiOjE1ODM1NTg3NDZ9.7eSkvXg-Qk4Y_ZlpOuKc-Notu6lXr-WxwGiAoDk1lvY";
        Claims claims=JwtUtils.checkJWT(token);
        if(claims!=null){
            String username=(String) claims.get("username");
            int id=(Integer)claims.get("id");
            String password=(String)claims.get("password");
            String empId=(String)claims.get("empId");
            System.out.println(id);
            System.out.println(username);
            System.out.println(empId);
            System.out.println(password);
        }else {
            System.out.println("解密失败，非法token！");
        }
    }
*/
    @Autowired
    private UserService service;
    @Test
    void checkLogin() throws Exception {
        String username="1001";
        String password="1458510486";
        JsonObject jsonObject=new JsonObject();
        User user=new User();
        boolean a=service.loginCheck(username,password);
        if(service.loginCheck(username,password)){
            user.setPassword(password);
            user.setUsername(username);
            String token=JwtUtils.geneJsonWebToken(user);
            jsonObject.addProperty("status","success");
        }else {
            jsonObject.addProperty("status", "error");
        }
    }
}
