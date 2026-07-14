package com.example.inventario.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

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

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_inventario;
        private int cantidad;
        private ProductoResponse producto;
        private TiendaResponse tienda;
    }
}
