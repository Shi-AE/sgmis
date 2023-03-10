package com.AE.sgmis;

import com.AE.sgmis.filter.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan("com.AE.sgmis.filter")
@MapperScan("com.AE.sgmis.mapper")
public class SgmisApplication implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(SgmisApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/private/*");
    }
}
