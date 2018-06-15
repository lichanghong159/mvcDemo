package com.lch.controller;

import com.lch.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    IUserService userService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        //model.addAttribute("msg", "Hello,World!");
        return "login";
    }
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(Model model, // 向前台页面传的值放入model中
                        HttpServletRequest request){ // 从前台页面取得的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(userService.login(username,password)){
            model.addAttribute("msg", username);
            return "success";
        }else{
            model.addAttribute("msg", "用户名/密码错误!");
            return "login";
        }
    }
}