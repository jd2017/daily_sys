package com.yun.config;

import com.yun.daily.secure.filter.SecureAuthenticationTokenFilter;
import com.yun.daily.secure.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

import javax.servlet.FilterConfig;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
/*spring会自动寻找同样类型的具体类注入这里就是SecureUserDetailsServiceImpl*/
    @Autowired
    private UserDetailsService userDetailsService;
    //注入数据源
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(this.userDetailsService)
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
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

//    @Bean
//    public RememberMeServices rememberMeServices(){
//        JdbcTokenRepositoryImpl rememberMeTokenRepository =new JdbcTokenRepositoryImpl();
//        rememberMeTokenRepository.setDataSource(dataSource);
//        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
//        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
//        PersistentTokenBasedRememberMeServices rememberMeServices =
//                new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", userDetailsService, rememberMeTokenRepository );
//
//        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
//        rememberMeServices.setParameter("remember-me");
//        return rememberMeServices ;
//    }

    @Bean
    public SecureAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        return new SecureAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()                                   // 记住我相关配置
                .tokenRepository(persistentTokenRepository())   // 设置TokenRepository
                // 配置Cookie过期时间
                .tokenValiditySeconds(250000)
                // 配置UserDetailsService
                .userDetailsService(userDetailsService);
        // 禁用缓存
        httpSecurity.headers().cacheControl();

        /*addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
        在 beforeFilter 之前添加 filter
        addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
        在 afterFilter 之后添加 filter
        addFilterAt(Filter filter, Class<? extends Filter> atFilter)
        在 atFilter 相同位置添加 filter， 此 filter 不覆盖 filter*/

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/static/**");//static下的静态资源不拦截
    }
}
