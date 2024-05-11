package com.aixbox.usercenter.controller;

import com.aixbox.usercenter.common.BaseResponse;
import com.aixbox.usercenter.common.ResultUtils;
import com.aixbox.usercenter.model.domain.User;
import com.aixbox.usercenter.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 魔王Aixbox
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('test')")
    public BaseResponse<String> register(@RequestBody User user) {
        userService.register(user);
        return ResultUtils.success("");
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user){
        //登录
        return userService.login(user);
    }

    @RequestMapping("/logout")
    public BaseResponse logout(){
        return userService.logout();
    }
}
