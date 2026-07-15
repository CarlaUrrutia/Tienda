package com.example.cupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CuponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CuponApplication.class, args);
    }
}
