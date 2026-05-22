package com.example.oferta.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OfertaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(min = 2, message = "La descripcion debe tener al menos 2 caracteres")
        private String descripcion;

        @Min(value = 0, message = "El descuento no puede ser negativo")
        private int descuento;

        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_oferta;
        private String descripcion;
        private int descuento;
        private int id_producto;
    }
}
