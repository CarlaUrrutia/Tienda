package com.ejemplo.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de Spring Security con Basic Auth.
 *
 * El usuario y contraseña se definen en application.properties:
 *   spring.security.user.name=admin
 *   spring.security.user.password=123
 *
 * En Postman: Authorization → Basic Auth → Username: admin / Password: 123
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF: no necesario para APIs REST stateless
            //Cross-Site Request Forgery
            .csrf(csrf -> csrf.disable())

            // Todas las rutas requieren autenticación
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )

            // Activar Basic Auth
            .httpBasic(Customizer.withDefaults())

            // Sin sesión: cada petición lleva sus credenciales (REST stateless)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}
