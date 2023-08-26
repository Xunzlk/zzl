package com.zzl.zzlblog.mapper;

import com.zzl.zzlblog.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserById(@Param("id") Long id);
    User getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
    User getUserByUserName(@Param("userName") String userName);
    void registerUser(@Param("nickName") String nickName, @Param("userName") String userName, @Param("password") String password);
    void uploadAvatar(@Param("id") Long id, @Param("avatarURL") String avatarURL);
    void updateDescription(@Param("id") Long id, @Param("description") String description);
    void updateNickName(@Param("id") Long id, @Param("nickName") String nickName);
    void updatePassword(@Param("id") Long id, @Param("password") String password);

}
