package com.nullpoint.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author nullpoint
 * @date 2020/6/10
 * @desc
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperGatewayApplication.class, args);
        System.out.println("zookeeper gateway started ......");
    }
}
