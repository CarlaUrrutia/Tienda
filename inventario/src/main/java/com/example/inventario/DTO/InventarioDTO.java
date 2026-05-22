package com.example.inventario.DTO;

import com.example.inventario.dto.ProductoResponse;
import com.example.inventario.dto.TiendaResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InventarioDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private int cantidad;
        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
        @Min(value = 1, message = "El id_tienda es obligatorio")
        private int id_tienda;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_inventario;
        private int cantidad;
        private ProductoResponse producto;
        private TiendaResponse tienda;
    }
}
