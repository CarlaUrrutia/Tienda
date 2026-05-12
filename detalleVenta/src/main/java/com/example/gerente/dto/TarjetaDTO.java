package com.example.gerente.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TarjetaDTO {
     @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la tarjeta no puede ser negativo")
        private int id_tarjeta;
        /*El char se valida?? ni idea preguntar al profe */
    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_tarjeta;
        private ClienteDTO cliente;
    }
}
