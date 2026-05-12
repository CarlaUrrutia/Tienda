package com.example.gerente.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CiudadDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El id de la ciudad es obligatorio")
        @Min(value = 0)
        private int id_ciudad;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_ciudad;
        private String nombre;

    }
}