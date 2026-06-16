package com.example.detalleventa.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.venta.url}")
    private String ventaUrl;

    @Value("${ms.producto.url}")
    private String productoUrl;

    @Bean
    public WebClient ventaWebClient() {
        return WebClient.builder().baseUrl(ventaUrl).build();
    }

    @Bean
    public WebClient productoWebClient() {
        return WebClient.builder().baseUrl(productoUrl).build();
    }
}
