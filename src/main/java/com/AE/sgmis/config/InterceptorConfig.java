package com.AE.sgmis.config;

import com.AE.sgmis.interceptor.LoginInterceptor;
import com.AE.sgmis.interceptor.XxpzInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private XxpzInterceptor xxpzInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/authority")
                .order(1);
        registry.addInterceptor(xxpzInterceptor)
                .addPathPatterns("/api/xxpz/**")
                .order(2);
    }
}
