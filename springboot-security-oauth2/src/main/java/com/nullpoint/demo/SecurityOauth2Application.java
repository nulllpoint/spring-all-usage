package com.nullpoint.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author nullpoint
 * @date 2020/6/11
 * @desc
 */

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class SecurityOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityOauth2Application.class, args);
        System.out.println("security oauth2 started ......");
    }
}
