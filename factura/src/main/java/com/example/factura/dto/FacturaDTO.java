package com.example.factura.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class FacturaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotNull(message = "La fecha es obligatoria")
        private Date fecha;
        @NotNull(message = "El total es obligatorio")
        @Min(value = 0, message = "El total no puede ser negativo")
        private Integer total;
        @NotNull(message = "El id_venta es obligatorio")
        @Min(value = 1)
        private Integer id_venta;
        @NotNull(message = "El id_cliente es obligatorio")
        @Min(value = 1)
        private Integer id_cliente;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private Long id;
        private Date fecha;
        private Integer total;
        private ClienteResponse cliente;
        private VentaResponse venta;
    }
}