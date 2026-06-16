package com.example.venta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.cliente.url}")
    private String clienteUrl;

    @Value("${ms.empleado.url}")
    private String empleadoUrl;

    @Bean
    public WebClient clienteWebClient() {
        return WebClient.builder().baseUrl(clienteUrl).build();
    }

    @Bean
    public WebClient empleadoWebClient() {
        return WebClient.builder().baseUrl(empleadoUrl).build();
    }
}
