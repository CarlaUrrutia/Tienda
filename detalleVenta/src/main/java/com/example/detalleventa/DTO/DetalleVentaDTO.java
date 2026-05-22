package com.example.detalleVenta.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DetalleVentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private int cantidad;

        @Min(value = 0, message = "El precio unitario no puede ser negativo")
        private int precio_unitario_venta;

        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;

        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_detalle;
        private int cantidad;
        private int precio_unitario_venta;
        private int id_venta;
        private int id_producto;
    }
}
