package com.example.venta.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "La fecha de venta es obligatoria")
        private Date fecha_venta;

        @NotNull(message = "El id_cliente es obligatorio")
        private Integer id_cliente;

        @NotNull(message = "El id_empleado es obligatorio")
        private Integer id_empleado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_venta;
        private Date fecha_venta;
        private ClienteDTO cliente;
        private EmpleadoDTO empleado;
    }
}