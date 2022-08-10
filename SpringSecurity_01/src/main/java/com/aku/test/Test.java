package com.aku.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //这里使用SpringSecurity提供的BCryptPasswordEncoder
        System.out.println(encoder.encode("123456"));
    }

}
