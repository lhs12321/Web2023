package com.example.web;

import com.example.web.springmvc.SigninInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan(basePackages = "kr.mjc.jacob.web")
@Slf4j
public class WebApplication extends SpringBootServletInitializer
        implements WebMvcConfigurer {
    /**
     * Spring Boot 구동
     */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    /**
     * SpringBootServletInitializer : for standalone container
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }

    /**
     * 인터셉터 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SigninInterceptor())
                .addPathPatterns("/user/myInfo", "/user/passwordEdit",
                        "/user/updatePassword", "/article/articleForm",
                        "/article/addArticle", "/article/articleEdit",
                        "/article/updateArticle", "/article/deleteArticle");
        log.info("signinInterceptor 등록");
    }
}