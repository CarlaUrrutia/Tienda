package com.example.devolucion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmpleadoDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El id del empleado es obligatorio")
        @Min(value = 0)
        private int  id_empleado;

        @NotBlank(message = "El nombre  es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "El apellido es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El apellido debe contener al menos 2 palabras")
        private String apellido;
        
        @NotBlank(message = "El sueldo del empleado es obligatorio")
        @Min(value = 0)
        private int  sueldo;

    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_empleado;
        private String nombre;
        private String apellido;
        private int sueldo;
        private TiendaDTO tienda; 
    }
}
