package com.aixbox.usercenter.service.impl;

import com.aixbox.usercenter.common.BaseResponse;
import com.aixbox.usercenter.common.ResultUtils;
import com.aixbox.usercenter.mapper.UserMapper;
import com.aixbox.usercenter.model.domain.LoginUser;
import com.aixbox.usercenter.model.domain.User;
import com.aixbox.usercenter.service.UserService;
import com.aixbox.usercenter.utils.JwtUtil;
import com.aixbox.usercenter.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Objects;

/**
* @author he
* @description 针对表【user(基础用户表)】的数据库操作Service实现
* @createDate 2024-05-11 17:06:18
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;

    @Override
    public void register(User user) {

    }

    @Override
    public BaseResponse login(User user) {
        //创建一个UsernamePasswordAuthenticationToken对象，将用户的用户名和密码作为参数传入。
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //调用authenticationManager.authenticate()方法对用户进行身份验证，返回一个Authentication对象。
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果authenticate对象为空，表示用户名或密码错误，抛出一个运行时异常。
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //从authenticate对象中获取登录用户的信息。
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获取用户id
        String userId = loginUser.getUser().getId().toString();

        //使用用户的ID生成一个JWT。
        String jwt = JwtUtil.createJWT(userId);

        //将登录用户的信息存入Redis缓存中，以便后续验证用户的身份。
        redisCache.setCacheObject("login:"+userId,loginUser);

        //创建一个包含JWT的响应结果，并返回给前端。
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return ResultUtils.success(map);
    }

    @Override
    public BaseResponse logout() {
        //获取SecurityContextHolder中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //获取用户id
        String userId = loginUser.getUser().getId().toString();


        //从redis中删除用户信息即可
        redisCache.deleteObject("login:"+userId);

        return ResultUtils.success("退出成功");
    }


}




