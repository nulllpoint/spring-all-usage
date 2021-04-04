package com.demo.srpingboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nullpoint
 * @date 2020/5/27
 * @desc
 */
@SpringBootApplication
public class ActutatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActutatorApplication.class);
        System.out.println("springboot-actuator service start ....");
    }
}
