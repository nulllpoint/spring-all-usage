package com.demo.module.auth.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

		@GetMapping("/hello")
		public String hello() {
			return "hello world";
		}
}
