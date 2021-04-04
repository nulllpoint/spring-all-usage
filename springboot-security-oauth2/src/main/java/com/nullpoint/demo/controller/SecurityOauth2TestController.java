package com.nullpoint.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nullpoint
 * @date 2020/6/12
 * @desc
 */
@RestController
@RequestMapping("/demo")
public class SecurityOauth2TestController {
    @GetMapping("/test")
    public String test() {
        return "security oauth2 test ......";
    }

}
