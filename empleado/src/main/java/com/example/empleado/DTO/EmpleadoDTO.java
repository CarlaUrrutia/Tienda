package com.example.empleado.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmpleadoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        private String nombre;

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
        private String apellido;

        @Min(value = 0, message = "El sueldo no puede ser negativo")
        private float sueldo;

        @Min(value = 1, message = "El id_tienda es obligatorio")
        private int id_tienda;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_empleado;
        private String nombre;
        private String apellido;
        private float sueldo;
        private int id_tienda;
    }
}
