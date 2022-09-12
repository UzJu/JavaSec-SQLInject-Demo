package com.uzju.dongtai_sql_inject_lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uzju.dongtai_sql_inject_lab.mapper")
public class DongtaiSqlInjectLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongtaiSqlInjectLabApplication.class, args);
    }

}
