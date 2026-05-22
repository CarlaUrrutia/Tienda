package com.example.devolucion.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class DevolucionDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El motivo es obligatorio")
        private String motivo;

        @Min(value = 0, message = "El monto del reembolso no puede ser negativo")
        private int monto_reembolso;

        @Min(value = 1, message = "La cantidad devuelta debe ser al menos 1")
        private int cantidad_devuelta;

        @Min(value = 1, message = "El id_empleado es obligatorio")
        private int id_empleado;

        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;

        @Min(value = 1, message = "El id_tarjeta es obligatorio")
        private int id_tarjeta;

        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;

        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_devolucion;
        private Date fecha_devolucion;
        private String motivo;
        private int monto_reembolso;
        private int cantidad_devuelta;
        private int id_empleado;
        private int id_cliente;
        private int id_tarjeta;
        private int id_venta;
        private int id_producto;
    }
}
