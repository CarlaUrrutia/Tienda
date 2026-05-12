package com.example.gerente.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegionDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la region no puede ser negativo")
        private int id_region;

        @NotBlank(message = "El nombre de la region es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;
        
    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_region;
        private String nombre;
    }
}
