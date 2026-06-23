package com.example.producto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id del producto no puede ser negativo")
        private int id_producto;
        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;
        @Min(value = 0, message = "El precio de venta no puede ser negativo")
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