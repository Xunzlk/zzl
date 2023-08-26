package com.zzl.zzlblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String description;
    private String avatarURL;
}
