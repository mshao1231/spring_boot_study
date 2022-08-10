package com.aku.config;

import com.aku.service.UserAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserAuthService service;

    //继承WebSecurityConfigurerAdapter，之后会进行配置
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //这里使用SpringSecurity提供的BCryptPasswordEncoder
        auth
                .userDetailsService(service)/*使用自定义的service实现类进行验证*/
                .passwordEncoder(new BCryptPasswordEncoder());/*加密*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色访问
                .antMatchers("/static/**").permitAll() //静态资源，使用permitAll来运行任何人访问
                .antMatchers("/**").hasRole("user") //所有请求必须登录冰球是user校色才可以访问
                .and()
                .formLogin() //配置form表单登录
                .loginPage("/login") //登录页面地址
                .loginProcessingUrl("/doLogin") //*form表单的提交地址
                .defaultSuccessUrl("/index",true)//登录成功后跳转的页面
                .permitAll() //登录页面允许所有人访问
                .and()
                .logout()
                .logoutUrl("/logout") //退出登录请求地址
                .logoutSuccessUrl("/login"); //退出后重定向的地址


    }
}
