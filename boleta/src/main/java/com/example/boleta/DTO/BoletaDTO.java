package com.example.boleta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

public class BoletaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_boleta;
        private ClienteResponse cliente;
        private VentaResponse venta;
}
}
