package com.zzl.zzlblog.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class SpaceController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    String fileUploadRootPath;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/space")
    public String space(){
        return "space";
    }
    @PostMapping("/space/uploadInfo")
    public String uploadInfo(MultipartFile file, @RequestParam("nickName") String nickName, @RequestParam("description") String description, Model model) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(!file.isEmpty())
        {
            String originalFilename = file.getOriginalFilename();
            String type = FileUtil.extName(originalFilename);
            String fileUploadPath = fileUploadRootPath+user.getId()+"/";
            File uploadParentFile = new File(fileUploadPath);
            if(!uploadParentFile.exists()){
                uploadParentFile.mkdirs();
            }
            String fileName = user.getId() + StrUtil.DOT + type;
            File uploadFile = new File(fileUploadPath+fileName);
            file.transferTo(uploadFile);

            userMapper.uploadAvatar(user.getId(), "/accounts/"+user.getId()+"/"+fileName);
        }
        userMapper.updateDescription(user.getId(), description);
        userMapper.updateNickName(user.getId(), nickName);
        user = userMapper.getUserById(user.getId());
        session.setAttribute("user", user);
        model.addAttribute("msg", "提交成功！请重新登录或禁用浏览器缓存以查看修改效果！");
        return "success";
    }
}
