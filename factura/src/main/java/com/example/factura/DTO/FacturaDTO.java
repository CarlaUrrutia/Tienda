package com.example.factura.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

public class FacturaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotNull(message = "La fecha es obligatoria")
        private Date fecha;
        @Min(value = 0, message = "El total no puede ser negativo")
        private int total;
        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_factura;
        private Date fecha;
        private int total;
        private ClienteResponse cliente;
        private VentaResponse venta;
    }
}