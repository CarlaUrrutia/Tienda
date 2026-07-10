package com.example.empleado.DTO;

import com.example.empleado.DTO.TiendaResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmpleadoDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @NotBlank(message = "El apellido es obligatorio")
        private String apellido;
        @Min(value = 0, message = "El sueldo no puede ser negativo")
        private float sueldo;
        @Min(value = 1, message = "El id_tienda es obligatorio")
        private int id_tienda;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_empleado;
        private String nombre;
        private String apellido;
        private float sueldo;
        private TiendaResponse tienda;
    }
}
