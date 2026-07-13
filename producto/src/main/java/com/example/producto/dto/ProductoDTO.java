package com.example.producto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotNull(message = "El precio de venta es obligatorio")
        @Min(value = 0, message = "El precio venta no puede ser negativo")
        private Integer precio_venta;

        @NotNull(message = "El id_proveedor es obligatorio")
        @Min(value = 1)
        private Integer id_proveedor;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_producto;
        private String nombre;
        private Integer precio_venta;
        private ProveedorDTO proveedor;
    }
}