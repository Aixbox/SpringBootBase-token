package com.aixbox.usercenter.filter;

import com.aixbox.usercenter.model.domain.LoginUser;
import com.aixbox.usercenter.utils.JwtUtil;
import com.aixbox.usercenter.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token：从HttpServletRequest对象中获取名为"token"的请求头信息。
        String token = httpServletRequest.getHeader("token");

        //检查token是否存在：如果token不存在，则直接放行请求，继续执行后续的过滤器或处理器。
        if(!StringUtils.hasText(token)){
            //放行操作
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //解析token：使用JwtUtil工具类解析token，获取其中的用户ID（userid）。
        String userId;

        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("非法的token");
        }

        //从Redis中获取用户信息：根据userId从Redis缓存中获取用户信息。
        LoginUser loginUser = redisCache.getCacheObject("login:" + userId);

        //检查用户是否登录：如果获取的用户信息为空，则抛出异常，表示用户未登录。
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }



        /**
         * 需要有一个Authentication类型的数据，所以先转换类型
         * UsernamePasswordAuthenticationToken参数解析如下：
         * principal：表示身份验证的主体，通常是用户的唯一标识，比如用户名、用户ID等。
         * credentials：表示身份验证的凭证，通常是用户的密码或其他认证信息。
         * authorities：表示用户的权限集合，即用户被授予的权限列表
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

        //存入SecurityContextHolder：将获取到的用户信息存入SecurityContextHolder中，以便后续的权限验证和授权操作。
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}