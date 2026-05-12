package com.example.gerente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DetalleVentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El detalle de venta no puede ser negativo")
        private int id_detalle;

        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private int cantidad;

        @Min(value = 0, message = "El precio unitario no puede ser negativo")
        private int precio_unitario_venta;
        
    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_detalle;
        private int cantidad;
        private int precio_unitario_venta;
        private VentaDTO venta; //clave foranea
        private ProductoDTO producto; //clave foranea
    }
}