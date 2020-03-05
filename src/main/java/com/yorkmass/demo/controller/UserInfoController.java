package com.yorkmass.demo.controller;

import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserInfoController {

    @Autowired
    private UserService service;
    @RequestMapping("/info")
    public String userInfo(HttpServletRequest request,Model model){
        String token= service.getTokenFromClient(request);
        User user=service.getUserByToken(token);
        model.addAttribute("role",service.getRoles(user.getUsername()).get(0));
        model.addAttribute("name",user.getName());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("birth",user.getBirth());
        model.addAttribute("gender",user.getGender()==1?"男":"女");
        model.addAttribute("email",user.getEmail());
        model.addAttribute("phone",user.getPhone());
        model.addAttribute("password",user.getPassword());
        return "thymeleaf/userinfo";
    }
}
