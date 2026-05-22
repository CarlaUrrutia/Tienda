package com.example.venta.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class VentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "La fecha de venta es obligatoria")
        private Date fecha_venta;

        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;

        @Min(value = 1, message = "El id_empleado es obligatorio")
        private int id_empleado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_venta;
        private Date fecha_venta;
        private int id_cliente;
        private int id_empleado;
    }
}
