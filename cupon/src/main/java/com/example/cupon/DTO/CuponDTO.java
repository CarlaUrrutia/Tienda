package com.example.cupon.DTO;

import com.example.cupon.DTO.ClienteResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class CuponDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El codigo es obligatorio")
        private String codigo;
        @Min(value = 0, message = "El descuento no puede ser negativo")
        private int descuento;
        private Date fecha_expiracion;
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_cupon;
        private String codigo;
        private int descuento;
        private Date fecha_expiracion;
        private ClienteResponse cliente;
    }
}
