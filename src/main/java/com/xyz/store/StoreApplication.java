package com.xyz.store;

import com.xyz.store.pojo.BaseEntity;
import com.xyz.store.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan("com.xyz.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext gtx = SpringApplication.run(StoreApplication.class, args);
        

    }

}
