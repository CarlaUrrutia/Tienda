package com.example.tarjeta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.cliente.url}")
    private String clienteUrl;

    @Bean
    public WebClient clienteWebClient() {
        return WebClient.builder().baseUrl(clienteUrl).build();
    }
}
