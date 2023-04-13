package com.AE.sgmis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan("com.AE.sgmis.filter")
@MapperScan("com.AE.sgmis.mapper")
public class SgmisApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SgmisApplication.class, args);
    }
}
