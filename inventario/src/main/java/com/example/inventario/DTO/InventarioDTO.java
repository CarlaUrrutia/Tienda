package com.example.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

public class InventarioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id del inventario  no puede ser negativo")
        private int id_inventario;

        @Min(value = 0, message = "El total del inventario no puede ser negativo")
        private int cantidad;

        
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_inventario;
        private int cantidad;
        private ProductoResponse producto;
        private TiendaResponse tienda;
    }
}