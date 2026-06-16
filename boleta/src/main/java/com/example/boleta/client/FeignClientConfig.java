package com.example.boleta.client;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración del FeignClient para boleta.
 * No requiere autenticación Basic Auth — los microservicios internos
 * se comunican libremente dentro de la red local.
 */
@Configuration
public class FeignClientConfig {
    // Configuración base de Feign para el módulo boleta
}
