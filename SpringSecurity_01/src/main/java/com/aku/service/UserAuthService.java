package com.aku.service;

import com.aku.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService{
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password =userMapper.getPasswordByUserName(s);/*从数据库根据用户查询密码*/
        System.out.println(s);
        System.out.println(password);
        if(password==null){
            throw  new UsernameNotFoundException("登录失败！用户名或密码错误！");
        }
        return User
                .withUsername(s)/*从数据库获取根据用户名获取密码*/
                .password(password)
                .roles("user")/*用户角色*/
                .build();
    }

}
