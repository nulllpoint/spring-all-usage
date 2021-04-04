package com.nullpoint.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nullpoint
 * @date 2020/6/10
 * @desc
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    public String test() {
        return "zookeeper client test ......";
    }

}
