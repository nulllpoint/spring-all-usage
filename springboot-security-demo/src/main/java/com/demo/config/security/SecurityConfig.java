package com.demo.config.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.utils.validate.ValidateCodeFilter;

/**
 * @description 用于暴露AuthenticationManager
 * @author liuhoujie
 * @date 2019年8月30日
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Resource
	private MyAuthenticationSucessHandler authenticationSuccessHandler;
	@Resource
	private MyAuthenticationFailureHandler authenticationFailureHandler;
	@Resource
	private ValidateCodeFilter validateCodeFilter;

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.formLogin() //表单方式
//			.and().authorizeRequests() //授权配置
//			.anyRequest() //所有请求
//			.authenticated() //都需要认证
//			;
		
//		http
//			.httpBasic() //http basic方式
//			.and().csrf().disable();
//			super.configure(http);
		
		
//	    http
//			.formLogin() // 表单登录
//	        .loginPage("/login.html")  // 指定了跳转到登录页面的请求URL
//	        .loginProcessingUrl("/login") // 对应登录页面form表单的action="/login"
//	        .successHandler(authenticationSuccessHandler) // 授权成功时的处理
//	        .failureHandler(authenticationFailureHandler) //授权失败时的处理
//	        .and()
//	        .authorizeRequests() // 授权配置
//	        .antMatchers("/login.html","/code/image").permitAll() //表示跳转到登录页面的请求不被拦截，否则会进入无限循环。
//	        .anyRequest()  // 所有请求
//	        .authenticated() // 都需要认证
//	    	.and().csrf().disable();
//	}
		
		
	    http
	    	//注入了ValidateCodeFilter，然后通过addFilterBefore方法将ValidateCodeFilter验证码校验过滤器添加到了UsernamePasswordAuthenticationFilter前面。
	    	.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
	    	.formLogin() // 表单登录
	        .loginPage("/login.html")  // 指定了跳转到登录页面的请求URL
	        .loginProcessingUrl("/login") // 对应登录页面form表单的action="/login"
	        .successHandler(authenticationSuccessHandler) // 授权成功时的处理
	        .failureHandler(authenticationFailureHandler) //授权失败时的处理
	        .and()
	        .authorizeRequests() // 授权配置
	        .antMatchers("/login.html","/code/image").permitAll() //表示跳转到登录页面的请求不被拦截，否则会进入无限循环。
	        .anyRequest()  // 所有请求
	        .authenticated() // 都需要认证
			.and().csrf().disable();
	}

}
