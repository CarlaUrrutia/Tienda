package com.example.detalleventa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DetalleVentaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DetalleVentaApplication.class, args);
    }
}
