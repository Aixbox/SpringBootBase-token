package com.aixbox.usercenter.service.impl;

import com.aixbox.usercenter.mapper.MenuMapper;
import com.aixbox.usercenter.mapper.UserMapper;
import com.aixbox.usercenter.model.domain.LoginUser;
import com.aixbox.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //根据手机号查询用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO 根据用户查询权限信息 添加到LoginUser中
        //List<String> perms = menuMapper.findPermsByUserId(user.getId());
        List<String> perms = Arrays.asList("test1");

        //封装成UserDetails对象返回
        return new LoginUser(user, perms);
    }

}