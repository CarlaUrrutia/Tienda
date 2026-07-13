package com.example.tarjeta.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


public class TarjetaDTO {
     @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la tarjeta no puede ser negativo")
        private int id_tarjeta;
        /*El char se valida?? ni idea preguntar al profe */
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_tarjeta;
        private ClienteDTO cliente;
    }
}
