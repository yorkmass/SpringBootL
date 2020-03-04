package com.yorkmass.demo.service.impl;

import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.mapper.MyUserMapper;
import com.yorkmass.demo.service.UserService;
import com.yorkmass.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private MyUserMapper myUserMapper;

    /*
    和数据库的password进行比较，相同就ture
     */
    @Override
    public boolean loginCheck(String username, String password) throws Exception{
        try{
            String Dpassword=myUserMapper.getPasswordByUsername(username);
            if(Dpassword.equals(password)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public String getPassword(String username) {
        return myUserMapper.getPasswordByUsername(username);
    }

    @Override
    public String getRole(String username) {
        return myUserMapper.getRolebyUsername(username);
    }

    @Override
    public String getTokenFromClient(HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        String token = null;
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    token=cookie.getValue();
                }
            }
        }
        return token;
    }

    @Override
    public String getUsernameByToken(String token) {
        Claims claims= JwtUtils.checkJWT(token);
        return (String) claims.get("username");
    }

    @Override
    public User getUser(String username) {
        return myUserMapper.getUserByUsername(username);
    }

    @Override
    public User getUserByToken(String token) {
        String username=getUsernameByToken(token);
        return getUser(username);
    }
}
