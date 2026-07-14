package com.example.cliente.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

public class ClienteDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        private String nombre;

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
        private String apellido;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        private String email;
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
public static class Response extends RepresentationModel<Response> {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String email;
}
    
}
