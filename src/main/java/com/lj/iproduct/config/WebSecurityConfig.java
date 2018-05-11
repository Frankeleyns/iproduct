package com.lj.iproduct.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


/*@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	*//**
     * .antMatchers(HttpMethod.OPTIONS).permitAll()：任何程序允许的http请求都允许访问，HttpMethod.OPTIONS获取服务端支持的HTTP请求方法
     * .anyRequest().authenticated()：尚未匹配的任何URL要求用户进行身份验证
     * .httpBasic()：允许用户使用HTTP基本验证进行认证 
     *  csrf().disable()：关闭csrf(伪造跨站域请求)防范
     *  退出登录输入"/logout"
     *//*
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().authorizeRequests() 
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .anyRequest().authenticated()
        .and().httpBasic()
        .and().csrf().disable();
		
	}
	
	
}*/