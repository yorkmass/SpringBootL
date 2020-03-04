package com.yorkmass.demo.service;

import com.yorkmass.demo.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    boolean loginCheck(String username,String password) throws Exception;
    String getPassword(String username);
    String getRole(String username);
    String getTokenFromClient(HttpServletRequest request);
    String getUsernameByToken(String token);
    User getUser(String username);
    User getUserByToken(String token);
}
