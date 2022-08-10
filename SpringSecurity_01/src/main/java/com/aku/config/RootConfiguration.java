package com.aku.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.aku.mapper")
@ComponentScan("com.aku.service")
public class RootConfiguration {

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource =  new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_boot_db?serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    /*定义Bean  SqlSessionFactoryBean 用于产生SqlSessionFactory对象*/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean (DataSource dataSource){
        SqlSessionFactoryBean sessionFactoryBean =  new SqlSessionFactoryBean();
        /*设置数据源*/
        sessionFactoryBean.setDataSource(dataSource);
        return  sessionFactoryBean;
    }

}
