package com.AE.sgmis.config;

import com.AE.sgmis.interceptor.AdminInterceptor;
import com.AE.sgmis.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接收所有api
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/authority")
                .excludePathPatterns("/api/login/free")
                .order(1);

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/user/**")
                .addPathPatterns("/api/login/admin")
                .order(2);
    }
}
