package com.example.myBlog.config;

import com.example.myBlog.interceptor.LoginInterceptor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 告诉 Spring 这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")  // 1. 拦截所有接口
                .excludePathPatterns("/user/login",
                        "/user/register",
                        "/doc.html",           // 1. 文档主页
                        "/webjars/**",         // 2. 文档用的 CSS/JS 静态资源
                        "/v3/api-docs/**" );     // 3. 文档查数据的接口); // 2. 放行登录接口 (不然没人能登录了)
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("全部接口") // 分组名称
                .pathsToMatch("/**") // 匹配所有路径
                .packagesToScan("com.example.myBlog.controller") // 填你 Controller 所在的包名
                .build();
    }
}