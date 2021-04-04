package com.nullpoint.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author nullpoint
 * @date 2020/6/9
 * @desc
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
        System.out.println("zookeeper client started ......");
    }
}
