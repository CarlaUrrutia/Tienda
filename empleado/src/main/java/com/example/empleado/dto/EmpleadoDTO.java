package com.example.empleado.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmpleadoDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id del empleado es obligatorio")
        private int id_empleado;
        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;
        @NotBlank(message = "El apellido es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El apellido debe contener al menos 2 palabras")
        private String apellido;
        @Min(value = 0, message = "El sueldo del empleado es obligatorio")
        private int sueldo;
        @Min(value = 1, message = "El id del rol es obligatorio")
        private int id_rol;
        @Min(value = 1, message = "El id de la tienda es obligatorio")
        private int id_tienda;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_empleado;
        private String nombre;
        private String apellido;
        private int sueldo;
        private TiendaResponse tienda;
        private RolResponse rol;
    }
}