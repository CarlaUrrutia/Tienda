package com.example.gerente.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id del producto no puede ser negativo")
        private int id_producto;

        @NotBlank(message = "La descripcion es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        

        @Min(value = 0, message = "El precio venta  del  producto no puede ser negativo")
        private int precio_venta;

        
    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_producto;
        private String nombre;
        private int precio_venta;
        private ProveedorDTO proveedor; // clave foranea
    }
}
