package com.example.envio.DTO;

import com.example.envio.DTO.ClienteResponse;
import com.example.envio.DTO.EmpleadoResponse;
import com.example.envio.DTO.VentaResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class EnvioDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotNull(message = "La fecha de envio es obligatoria")
        private Date fecha_envio;
        @NotNull(message = "La fecha estimada es obligatoria")
        private Date fecha_estimada_entrega;
        @NotBlank(message = "El estado es obligatorio")
        private String estado;
        @NotBlank(message = "La direccion es obligatoria")
        private String direccion_destino;
        @Min(value = 1, message = "El id_venta es obligatorio")
        private int id_venta;
        @Min(value = 1, message = "El id_cliente es obligatorio")
        private int id_cliente;
        @Min(value = 1, message = "El id_empleado es obligatorio")
        private int id_empleado;
        @Min(value = 1, message = "El id_ciudad es obligatorio")
        private int id_ciudad;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_envio;
        private Date fecha_envio;
        private Date fecha_estimada_entrega;
        private String estado;
        private String direccion_destino;
        private VentaResponse venta;
        private ClienteResponse cliente;
        private EmpleadoResponse empleado;
        private int id_ciudad;
    }
}
