package com.yorkmass.demo.service;

import com.yorkmass.demo.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    boolean loginCheck(String username,String password) throws Exception;
    String getPassword(String username);
    List<String> getRoles(String username);
    String getTokenFromClient(HttpServletRequest request);
    String getUsernameByToken(String token);
    User getUser(String username);
    User getUserByToken(String token);
}
