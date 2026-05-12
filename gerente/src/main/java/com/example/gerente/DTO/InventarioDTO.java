package com.example.gerente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_empleado;
        private int edad;
        private TiendaDTO tienda; // clave foranea
        private ProductoDTO producto; //clave foranea
    }
}