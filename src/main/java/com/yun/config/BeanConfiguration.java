package com.yun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

@Configuration
public class BeanConfiguration {
//    @Bean
//    public RequestCache requestCache(){
//        return new HttpSessionRequestCache();
//    }

//    @Bean
//    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
//        return new LoginUrlAuthenticationEntryPoint("/login");
//    }

//    @Bean
//    public ExceptionTranslationFilter exceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) throws Exception{
//        return new ExceptionTranslationFilter(authenticationEntryPoint);
//    }
//    @Bean
//    public RequestCacheAwareFilter requestCacheAwareFilter() throws Exception{
//        return new RequestCacheAwareFilter();
//    }

}
