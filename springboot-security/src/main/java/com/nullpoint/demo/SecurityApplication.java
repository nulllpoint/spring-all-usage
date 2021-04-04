package com.nullpoint.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author nullpoint
 * @date 2020/6/7
 * @desc
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
        System.out.println("springboot security demo start ......");
    }
}
