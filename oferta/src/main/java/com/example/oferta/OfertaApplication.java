package com.example.oferta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OfertaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfertaApplication.class, args);
    }
}
