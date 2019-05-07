package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.jk.mapper")
@EnableTransactionManagement
public class HouseRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseRentApplication.class, args);
    }

}
