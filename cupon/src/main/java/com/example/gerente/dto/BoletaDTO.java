package com.example.gerente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoletaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El id es obligatorio")
        @Min(value = 0)
        private int id_boleta;

    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign. queeeeeeeee
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_boleta;
    }
}