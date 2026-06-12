package com.example.devolucion.DTO;

import com.example.devolucion.DTO.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

public class DevolucionDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El motivo es obligatorio")
        private String motivo;
        @Min(value = 0, message = "El monto no puede ser negativo")
        private int monto_reembolso;
        @Min(value = 1, message = "La cantidad devuelta debe ser al menos 1")
        private int cantidad_devuelta;
        @Min(value = 1) private int id_empleado;
        @Min(value = 1) private int id_cliente;
        @Min(value = 1) private int id_tarjeta;
        @Min(value = 1) private int id_venta;
        @Min(value = 1) private int id_producto;
    }

    @Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @AllArgsConstructor
    public static class Response extends RepresentationModel<Response> {
        private int id_devolucion;
        private Date fecha_devolucion;
        private String motivo;
        private int monto_reembolso;
        private int cantidad_devuelta;
        private EmpleadoResponse empleado;
        private ClienteResponse cliente;
        private TarjetaResponse tarjeta;
        private VentaResponse venta;
        private ProductoResponse producto;
    }
}
