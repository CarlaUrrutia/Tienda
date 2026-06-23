package com.example.tienda.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class TiendaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id de la tienda no puede ser negativo")
        private int id_tienda;

        @NotBlank(message = "El nombre de la tienda es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre_tienda;

        @NotBlank(message = "La ubicacion de la tienda es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La ubicacion debe contener al menos 2 palabras")
        private String ubicacion;

        private Date horario_apertura;

        @NotBlank(message = "Las politicas de la tienda son obligatorias")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "Las politicas deben contener al menos 2 palabras")
        private String politicas;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_tienda;
        private String nombre_tienda;
        private String ubicacion;
        private Date horario_apertura;
        private String politicas;
    }
}