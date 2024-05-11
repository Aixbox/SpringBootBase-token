package com.aixbox.usercenter;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;

@SpringBootTest
class UserCenterApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {

    }

    @Test
    public void testPasswordEncoder(){

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //通过encode方法可以进行加密
        String password1 = passwordEncoder.encode("123456");
        String password2 = passwordEncoder.encode("123456");

        //同一个明文加密多次结果是不同的
        System.out.println(password1);
        System.out.println(password2);

        //明文和密码校验，模拟登录
        boolean flage = passwordEncoder.matches(
                "123456",
                "$2a$10$XdsSquwuwKZj7rbld7D3keHsMcAJT9PyvEwpOFTrrw0YoxZ1BU5aO");

        System.out.println(flage);
    }

}
