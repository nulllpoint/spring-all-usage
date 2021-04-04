package com.demo.swagger.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nullpoint
 * @date 2020/8/3
 * @desc
 */

@Api(tags = "测试")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation(value="测试1")
    @PostMapping(params = "method=demo1")
    public void demo1() {

    }


    @ApiOperation(value="测试2")
    @PostMapping(params = "method=demo2")
    public void demo2() {

    }
}
