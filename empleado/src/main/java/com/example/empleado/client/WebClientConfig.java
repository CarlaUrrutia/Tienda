package com.example.empleado.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.tienda.url}")
    private String tiendaUrl;

    @Bean
    public WebClient tiendaWebClient() {
        return WebClient.builder().baseUrl(tiendaUrl).build();
    }
}
