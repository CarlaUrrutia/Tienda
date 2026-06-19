package com.example.tarjeta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

public class TarjetaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id de la tarjeta no puede ser negativo")
        private int id_tarjeta;

        @NotNull(message = "El tipo de tarjeta es obligatorio")
        private char tipo;

        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Response extends RepresentationModel<Response> {
        private int id_tarjeta;
        private char tipo;
        private ClienteResponse cliente;
    }
}