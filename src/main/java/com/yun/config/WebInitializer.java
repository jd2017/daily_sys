package com.yun.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

//WebApplicationInitializer是Spring提供用来配置ServLet3.0配置的接口，替代web.xml
//实现此接口会自动被SpringServletContainerInitializer(启动Servlet3.0)获取到
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //新建WebApplicationContext，注册配置类，并将其和当前servletContext关联
        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
        ctx.register(WebInitializer.class);
        ctx.setServletContext(servletContext);
        //注册SpringMVC的DispatcherServlet
        Dynamic servlet=servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//开启异步方法支持
    }
}
