package com.example.tarjeta.DTO;

import jakarta.validation.constraints.*;
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

        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_tarjeta;
        private char tipo;
        private int id_cliente;
    }
}
