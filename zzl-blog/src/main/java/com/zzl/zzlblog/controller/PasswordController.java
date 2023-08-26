package com.zzl.zzlblog.controller;

import com.zzl.zzlblog.bean.User;
import com.zzl.zzlblog.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class PasswordController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/space/changePassword")
    public String showPasswordForm(){
        return "passwdChanger";
    }

    @PostMapping("/space/changePasswd")
    public String changePassword(@RequestParam("originalPassword") String originalPassword, @RequestParam("newPassword") String newPassword, @RequestParam("rePassword") String rePassword, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(!user.getPassword().equals(originalPassword))
        {
            model.addAttribute("msg", "原密码错误！");
            return "fail";
        }
        if(!newPassword.equals(rePassword))
        {
            model.addAttribute("msg", "两次密码输入不一致！");
            return "fail";
        }
        userMapper.updatePassword(user.getId(), newPassword);
        user = userMapper.getUserById(user.getId());
        session.setAttribute("user", user);
        model.addAttribute("msg", "修改成功！");
        return "success";
    }
}
