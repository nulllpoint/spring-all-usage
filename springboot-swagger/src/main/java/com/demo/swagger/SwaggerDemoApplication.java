package com.demo.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author nullpoint
 * @date 2020/8/3
 * @desc
 */
@EnableOpenApi
@SpringBootApplication
public class SwaggerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerDemoApplication.class);
        System.out.println("srpingboot swagger demo start ... ");
    }
}
