package com.yorkmass.demo.controller;

import com.yorkmass.demo.domain.User;
import com.yorkmass.demo.exception.NotFoundException;
import com.yorkmass.demo.service.UserService;
import com.yorkmass.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String login(Model model){
        return "thymeleaf/login";
    }
    //@CookieValue("token") String token 注解获取cookies
    /*
    主页，判断是否登录过，如果token为空，返回登录页面
     */
    @RequestMapping("/index")
    public String indexPage(HttpServletRequest request,Model model){
        String token = service.getTokenFromClient(request);
        if(token!=null){
            String username=service.getUsernameByToken(token);
            model.addAttribute("rolename",service.getRoles(username).get(0));
            return "thymeleaf/index";
        }else {
            return "thymeleaf/login";
        }
    }
    /*
    欢迎页面，用token获取用户信息
     */
    @RequestMapping("/welcome")
    public String welcomePage(HttpServletRequest request,Model model){
        String token = service.getTokenFromClient(request);
        if(token!=null){
            String username=service.getUsernameByToken(token);
            User user=service.getUser(username);
            String role=service.getRoles(username).get(0);
            model.addAttribute("name",user.getName());
            model.addAttribute("role",role);
        }
        return "thymeleaf/welcome";
    }

    /**
     * 错误页面示例
     * @return

    @GetMapping("/sss")
    public String index(){
        int i=9/0;
        String blog=null;
        if(blog==null){
            throw new NotFoundException("博客不存在");
        }
        return "welcome";
    }
    */
}
