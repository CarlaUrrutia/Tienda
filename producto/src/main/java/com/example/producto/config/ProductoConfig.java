package com.example.producto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProductoConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para pruebas en microservicios
            .authorizeHttpRequests(auth -> auth
                // Permitimos el acceso total a las rutas de Swagger configuradas y por defecto
                .requestMatchers("/doc/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Permitimos tus endpoints de la API v2 temporalmente para pruebas
                .requestMatchers("/api/v2/productos/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}