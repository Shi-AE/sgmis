package com.AE.sgmis.config;

import com.AE.sgmis.interceptor.AdminInterceptor;
import com.AE.sgmis.interceptor.BlackInterceptor;
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
    @Autowired
    private BlackInterceptor blackInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //ip黑名单检测拦截器
        registry.addInterceptor(blackInterceptor)
                .addPathPatterns("/api/**")
                .order(1);

        //请求登录验证拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/authority")
                .excludePathPatterns("/api/login/free")
                .order(2);

        //管理员请求拦截器
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/user/**")
                .addPathPatterns("/api/login/admin")
                .order(3);
    }
}
