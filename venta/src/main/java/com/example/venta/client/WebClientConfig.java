package com.example.venta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

/**
 * Configuracion del WebClient para consumir ms-venta.
 *
 * WebClient es el cliente HTTP moderno de Spring (reemplaza a RestTemplate).
 * Aqui lo configuramos con:
 *   - baseUrl: apunta a ms-venta (localhost:8082)
 *   - Basic Auth: agrega el header Authorization en cada peticion
 */
@Configuration
public class WebClientConfig {

    @Value("${ms.venta.url}")
    private String ventaUrl;

    @Value("${ms.venta.user}")
    private String ventaUser;

    @Value("${ms.venta.password}")
    private String ventaPassword;

    @Bean
    public WebClient ventaWebClient() {

        String credenciales = ventaUser + ":" + ventaPassword;
        String basicAuth = "Basic " +
                Base64.getEncoder().encodeToString(credenciales.getBytes());

        return WebClient.builder()
                .baseUrl(ventaUrl)
                .defaultHeader("Authorization", basicAuth)
                .build();
    }
}
