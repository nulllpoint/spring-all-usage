package com.nullpoint.demo.config;

import com.nullpoint.demo.entiy.User;
import com.nullpoint.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nullpoint
 * @date 2020/6/8
 * @desc
 */
@Slf4j
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : '{}' verify", username);
        User user = userMapper.selectUserByName(username);

        if (user == null) {
            return null;
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("p1").build();
        return userDetails;
//        return new User(username,passwordEncoder.encode("admin") ,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("p1"));
    }


}
