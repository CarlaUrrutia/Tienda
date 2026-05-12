package com.example.gerente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FacturaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la factura no puede ser negativo")
        private int id_factura;

        @Min(value = 0, message = "La venta de la facatura no puede ser negativa")
        private int venta;

        @Min(value = 0, message = "El total de la factura no puede ser negativo")
        private int total;
    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_factura;
        private int venta;
        private int total;
        private ClienteDTO cliente; // clave foranea
        private VentaDTO venta; //clave foranea
    }
}