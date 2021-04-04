package com.nullpoint.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * 安全配置
 * 由于Spring boot starter自动装配机制，这里无需使用@EnableWebSecurity，WebSecurityConfig
 * spring security提供了用户名密码登录、退出、会话管理等认证功能，只需要配置即可使用
 * WebSecurityConfig，安全配置的内容包括：用户信息、密码编码器、安全拦截机制
 *
 * @author nullpoint
 * @date 2020/6/7
 * @desc
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }*/

    /**
     * 密码编辑器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决由与csrf 导致druid监控台无法登录访问
     *
     * @param http
     * @throws Exception
     */
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .and()
        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            //放行这几种请求
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            //放行rest请求，当然后面rest与web将会分开，到时这里可以删除
            private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("^/.*", null);

            @Override
            public boolean matches(HttpServletRequest request) {
                if (allowedMethods.matcher(request.getMethod()).matches()) {
                    return false;
                }

                String servletPath = request.getServletPath();
                if (servletPath.contains("/druid")) {
                    return false;
                }
                return !unprotectedMatcher.matches(request);
            }
        });

    }*/


    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
