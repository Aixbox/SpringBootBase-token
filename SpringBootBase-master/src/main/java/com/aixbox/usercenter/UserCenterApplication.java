package com.aixbox.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aixbox.usercenter.mapper")
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "             _          _\n" +
                "     /\\     (_)        | |\n" +
                "    /  \\     _  __  __ | |__     ___   __  __\n" +
                "   / /\\ \\   | | \\ \\/ / | '_ \\   / _ \\  \\ \\/ /\n" +
                "  / ____ \\  | |  >  <  | |_) | | (_) |  >  <\n" +
                " /_/    \\_\\ |_| /_/\\_\\ |_.__/   \\___/  /_/\\_\\");
    }
}
