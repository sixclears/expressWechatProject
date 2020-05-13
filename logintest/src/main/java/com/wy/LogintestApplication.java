package com.wy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wy.dao")
@SpringBootApplication
public class LogintestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogintestApplication.class, args);
    }

}
