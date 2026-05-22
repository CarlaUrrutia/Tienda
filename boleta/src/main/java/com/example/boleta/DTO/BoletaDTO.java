package com.example.boleta.DTO;

import com.example.boleta.dto.ClienteResponse;
import com.example.boleta.dto.VentaResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoletaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_boleta;
        private ClienteResponse cliente;
        private VentaResponse venta;
    }
}
