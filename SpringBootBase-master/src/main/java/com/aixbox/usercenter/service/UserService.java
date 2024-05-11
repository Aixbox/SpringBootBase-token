package com.aixbox.usercenter.service;

import com.aixbox.usercenter.common.BaseResponse;
import com.aixbox.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;


/**
* @author he
* @description 针对表【user(基础用户表)】的数据库操作Service
* @createDate 2024-05-11 17:06:18
*/
public interface UserService extends IService<User> {

    void register(User user);

    BaseResponse login(User user);

    BaseResponse logout();

}
