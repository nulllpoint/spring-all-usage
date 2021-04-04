package com.demo.config.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description security框架授权成功处理
 * @author liuhoujie
 * @date 2019年9月1日
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
	
	@Resource
	ObjectMapper mapper;
	
//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(mapper.writeValueAsString(authentication));
		/*
		 *controller层使用：
		 *		SecurityContextHolder.getContext().getAuthentication();
		 *获取授权信息；
		 */
//		redirectStrategy.sendRedirect(request, response, "/action");
		
	}

}
