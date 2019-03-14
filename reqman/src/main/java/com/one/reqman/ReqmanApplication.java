package com.one.reqman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * {@link SpringBootApplication}
 * 是Spring Boot项目的核心注解，目的是开启自动配置
 */
@SpringBootApplication
public class ReqmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReqmanApplication.class, args);
    }

}
