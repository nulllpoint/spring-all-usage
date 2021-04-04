package com.nullpoint.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nullpoint
 * @date 2020/6/7
 * @desc
 */
@RestController
public class TestController {


    @GetMapping("/hello")
    public String hello() {
        return "hello springboot security ...";
    }

    @PreAuthorize("hasAuthority('p1')")
    @GetMapping("p1")
    public String p1() {
        return " p1 ......";
    }

    @PreAuthorize("hasAuthority('p2')")
    @GetMapping("p2")
    public String p2() {
        return " p2 ......";
    }

    @PreAuthorize("hasAuthority('p1') or hasAuthority('p2')")
    @GetMapping("p3")
    public String p3() {
        return " p1 or p2 ......";
    }

    @PreAuthorize("hasAuthority('p1') and hasAuthority('p2')")
    @GetMapping("p4")
    public String p4() {
        return " p1 and p2 ......";
    }

}