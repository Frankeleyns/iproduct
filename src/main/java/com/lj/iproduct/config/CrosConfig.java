package com.lj.iproduct.config;

import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;  


//跨域访问配置
@Configuration  
public class CrosConfig implements WebMvcConfigurer {  

    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("*")  
                .maxAge(3600);  
    }  

}
