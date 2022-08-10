package com.aku.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
@ComponentScan("com.aku.controller")
public class MvcConfiguration implements WebMvcConfigurer {


    /* 我们需要使用thymeleaf作为视图解析器，解析html页面 */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(@Autowired SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setCharacterEncoding("utf-8");
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);
        return thymeleafViewResolver;
    }

    /*配置模板解析器*/
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setPrefix("/WEB-INF/template/");
        springResourceTemplateResolver.setCharacterEncoding("utf-8");
        return springResourceTemplateResolver;
    }

    /*配置模板引擎*/
    @Bean
    public SpringTemplateEngine springTemplateEngine(@Autowired ITemplateResolver iTemplateResolver){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(iTemplateResolver);
        return springTemplateEngine;
    }

    /*开启静态资源处理*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
}
