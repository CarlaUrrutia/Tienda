package com.example.factura.DTO;

import com.example.factura.dto.ClienteResponse;
import com.example.factura.dto.VentaResponse;
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
        @Min(value = 0, message = "El total no puede ser negativo")
        private int total;
        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_factura;
        private Date fecha;
        private int total;
        private ClienteResponse cliente;
        private VentaResponse venta;
    }
}
