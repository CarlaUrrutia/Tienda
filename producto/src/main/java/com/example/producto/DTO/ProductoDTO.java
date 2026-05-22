package com.example.producto.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @Min(value = 0, message = "El precio no puede ser negativo")
        private int precio_venta;
        @Min(value = 1, message = "El id_proveedor es obligatorio")
        private int id_proveedor;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_producto;
        private String nombre;
        private int precio_venta;
        private ProveedorResponse proveedor;
    }
}
