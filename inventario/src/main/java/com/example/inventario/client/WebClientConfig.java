package com.example.inventario.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.producto.url}")
    private String productoUrl;

    @Value("${ms.tienda.url}")
    private String tiendaUrl;

    @Bean
    public WebClient productoWebClient() {
        return WebClient.builder().baseUrl(productoUrl).build();
    }

    @Bean
    public WebClient tiendaWebClient() {
        return WebClient.builder().baseUrl(tiendaUrl).build();
    }
}
