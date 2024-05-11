package com.aixbox.usercenter.security;

import com.aixbox.usercenter.common.ResponseResult;
import com.aixbox.usercenter.common.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //使用定义的响应实体类创建对象返回
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "用户权限不足");

        //转换成json格式
        String s = JSON.toJSONString(result);

        //设置授权失败响应
         WebUtils.renderString(response,s);
    }
}