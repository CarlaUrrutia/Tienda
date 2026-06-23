package com.example.oferta.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OfertaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "La descripcion es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La descripcion debe contener al menos 2 palabras")
        private String descripcion;

        @NotNull(message = "El descuento es obligatorio")
        @Min(value = 0, message = "El descuento no puede ser negativo")
        private Integer descuento;

        @NotNull(message = "El id_producto es obligatorio")
        @Min(value = 1)
        private Integer id_producto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id;
        private String descripcion;
        private Integer descuento;
        private ProductoDTO producto;
    }
}