package com.example.tarjeta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TarjetaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El tipo de tarjeta es obligatorio")
        private Character tipo;

        @NotNull(message = "El id_cliente es obligatorio")
        private Integer id_cliente;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_tarjeta;
        private Character tipo;
        private ClienteDTO cliente;
    }
}