package com.wen.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 13:29
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 定义密码的编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // 配置安全拦截机制

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()

//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                // 所有 /r/** 的请求必须认证通过
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
                // 允许表单登录
                .formLogin()
                // 登录的页面url
                .loginPage("/login-view")
                // 表单提交的url
                .loginProcessingUrl("/login")
                // 自定义登录成功的页面地址
                .successForwardUrl("/login-success")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .logout()
                // 退出的url
                .logoutUrl("/logout")
                // 退出成功 定向的地址
                .logoutSuccessUrl("/login-view?logout");

    }


}
