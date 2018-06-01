package com.yun.config;

import com.yun.daily.secure.filter.SecureAuthenticationTokenFilter;
import com.yun.daily.secure.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
/*spring会自动寻找同样类型的具体类注入这里就是SecureUserDetailsServiceImpl*/
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                //使用BCrypt进行密码的hash
                //使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载BCrypt密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecureAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        return new SecureAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600)
                .key("reportKey");
        ;
        // 禁用缓存
//        httpSecurity.headers().cacheControl();

        /*addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
        在 beforeFilter 之前添加 filter
        addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
        在 afterFilter 之后添加 filter
        addFilterAt(Filter filter, Class<? extends Filter> atFilter)
        在 atFilter 相同位置添加 filter， 此 filter 不覆盖 filter*/

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
