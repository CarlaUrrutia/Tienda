package com.example.gerente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CuponDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {


        @Min(value = 0, message = "El id del cupon no puede ser negativa")
        private int id_cupon;

        @NotNull(message = "El codigo del género es obligatorio")
        private String codigo;
        @Min(value = 0, message = "El descuento del cupon no puede ser negativo")
        private int descuento;

        
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_cupon;
        private String codigo;
        private int descuento;
        private ClienteDTO cliente; // Clave foranea
    }
}
