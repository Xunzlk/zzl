package com.zzl.zzlblog.controller;

import com.zzl.zzlblog.bean.User;
import com.zzl.zzlblog.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request){
        User user = userMapper.getUserByUserNameAndPassword(userName, password);
        HttpSession session = request.getSession();
        if(user != null)
        {
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        request.setAttribute("msg", "账号或密码错误,请重新输入！");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
