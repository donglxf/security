package com.example.config;

import com.sun.crypto.provider.HmacMD5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig {

    @Bean
    public PasswordEncoder getPwdEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        passwordEncoder.upgradeEncoding();
        return passwordEncoder;
//        return new MessageDigestPasswordEncoder("MD5");
    }

//    @Bean
//    public void conf(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(MyUserDetails.class.newInstance()).passwordEncoder(getPwdEncoder());
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.formLogin().loginPage("/login")
//                .loginProcessingUrl("/webLogin") // 此请求会触发登陆逻辑验证，执行UserDetailsService的实现类验证逻辑
//                .successForwardUrl("/success").failureForwardUrl("/fail")
////                .usernameParameter("") // 自定义参数登陆参数
////                .passwordParameter("")
//        ;
//        return http
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/login").permitAll()
//                        .antMatchers("/webLogin").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf().disable()
//                .build();
        http.csrf().disable();
        return http
                //不通过Session获取SecurityContext
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                // 允许跨域
                .cors()
                .and()
                // 配置路劲是否需要认证
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/webLogin").permitAll()
                // 配置权限
//                .antMatchers("/hello2").hasAuthority("/hello2")
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
