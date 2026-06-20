package com.example.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmpleadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadoApplication.class, args);
	}

}
