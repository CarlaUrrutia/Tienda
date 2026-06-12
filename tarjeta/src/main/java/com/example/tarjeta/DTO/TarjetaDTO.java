package com.example.tarjeta.DTO;

import com.example.tarjeta.DTO.ClienteResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


public class TarjetaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El tipo es obligatorio")
        private Character tipo;
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_tarjeta;
        private char tipo;
        private ClienteResponse cliente;
    }
}
