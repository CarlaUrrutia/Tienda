package com.example.producto.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.proveedor.url}")
    private String proveedorUrl;

    @Bean
    public WebClient proveedorWebClient() {
        return WebClient.builder().baseUrl(proveedorUrl).build();
    }
}
