package com.aixbox.usercenter.security;

import com.aixbox.usercenter.common.ResponseResult;
import com.aixbox.usercenter.common.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");

        //转换为JSON
        String s = JSON.toJSONString(result);

        //响应给前端
        WebUtils.renderString(response,s);
    }
}