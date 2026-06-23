package com.example.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InventarioDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id del inventario no puede ser negativo")
        private int id_inventario;
        @Min(value = 0, message = "El total del inventario no puede ser negativo")
        private int cantidad;
        @Min(value = 1, message = "El id_tienda es obligatorio")
        private int id_tienda;
        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_inventario;
        private int cantidad;
        private TiendaResponse tienda;
        private ProductoResponse producto;
    }
}