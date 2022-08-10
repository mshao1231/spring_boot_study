package com.aku.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        managerBuilder.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("test")
                .password(encoder.encode("123456"))
                .roles("user");
    }
}
