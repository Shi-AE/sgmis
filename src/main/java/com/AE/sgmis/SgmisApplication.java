package com.AE.sgmis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.AE.sgmis.mapper")
public class SgmisApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SgmisApplication.class, args);
    }
}
