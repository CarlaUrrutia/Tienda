package com.example.rol.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

public class RolDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id del rol no puede ser negativo")
        private int id_rol;

        @NotBlank(message = "El nombre_rol es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre_rol debe contener al menos 2 palabras")
        private String nombre_rol;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Response extends RepresentationModel<Response> {
        private int id_rol;
        private String nombre_rol;
    }
}