package com.example.cliente.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Cliente",
        version = "1.0",
        description = "Documentación de endpoints del microservicio Cliente"
    )
)
public class OpenApiConfig {
}