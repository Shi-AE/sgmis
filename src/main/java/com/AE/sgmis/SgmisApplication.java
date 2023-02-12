package com.AE.sgmis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.AE.sgmis.mapper")
public class SgmisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgmisApplication.class, args);
    }

}
