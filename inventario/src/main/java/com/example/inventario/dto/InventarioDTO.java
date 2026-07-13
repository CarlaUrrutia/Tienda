package com.example.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InventarioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private Integer cantidad;

        @NotNull(message = "El id_producto es obligatorio")
        @Min(value = 1)
        private Integer id_producto;

        @NotNull(message = "El id_tienda es obligatorio")
        @Min(value = 1)
        private Integer id_tienda;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id;
        private Integer cantidad;
        private TiendaResponse tienda;
        private ProductoResponse producto;
    }
}