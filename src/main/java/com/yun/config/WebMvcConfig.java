package com.yun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@EnableWebMvc //开启springmvc 支持
public class WebMvcConfig implements WebMvcConfigurer {

//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("classpath:/templates/");
//        viewResolver.setSuffix(".html");
//        viewResolver.setCache(false);
//        viewResolver.setContentType("text/html");
//        viewResolver.setViewClass(JstlView.class);
//        return viewResolver;
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/").setViewName("/login");
//        registry.addViewController("/register").setViewName("register");
//    }

}
