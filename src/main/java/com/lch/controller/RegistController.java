package com.lch.controller;

import com.lch.entity.User;
import com.lch.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class RegistController {
    @RequestMapping(value="regist",method= RequestMethod.GET)
    public String regist(){
        return "regist";
    }
    @Autowired
    IUserService userService;
    @RequestMapping(value="registSuccess",method=RequestMethod.POST)
    public String registSuccess(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        User user = new User() ;
        user.setBirthday(new Date());
        user.setUsername(username);
        user.setPassword(password);
        user.setSex("男");
        if(userService.insertUser(user)>0){
            model.addAttribute("username", username);
            return "registSuccess";
        }else{
            return "regist";
        }
    }
}