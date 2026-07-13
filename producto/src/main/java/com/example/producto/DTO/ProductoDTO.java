package com.example.producto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

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

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
        public static class Response extends RepresentationModel<Response> {
             private int id_producto;
                private String nombre;
                private int precio_venta;
                private ProveedorResponse proveedor;
}
}
