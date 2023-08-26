package com.zzl.zzlblog.controller;


import com.zzl.zzlblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/register")
    public String showRegisterForm(){
        return "register";
    }

    @PostMapping("/regs")
    public String register(@RequestParam("nickName") String nickName, @RequestParam("userName") String userName, @RequestParam("password") String password, Model model){
        if(userMapper.getUserByUserName(userName)!=null){
            model.addAttribute("msg", "注册失败！用户名已存在！");
            return "register";
        };
        userMapper.registerUser(nickName, userName, password);
        if(userMapper.getUserByUserNameAndPassword(userName, password)!=null)
            return "login";
        model.addAttribute("msg", "注册失败！");
        return "register";
    }
}
