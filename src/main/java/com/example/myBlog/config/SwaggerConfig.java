package com.example.myBlog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("我的博客系统 API 文档")
                        .description("这是我从零开始写的第一个 Spring Boot 项目！")
                        .version("v1.0.0"));
    }
}