package com.Hackason.BankAccountProject.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus配置类
 */
@Configuration
@MapperScan("com.Hackason.BankAccountProject.mapper")
public class MyBatisPlusConfig {
}
