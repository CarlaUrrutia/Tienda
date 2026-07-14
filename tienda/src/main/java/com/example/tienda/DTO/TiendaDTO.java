package com.example.tienda.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

public class TiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre de la tienda es obligatorio")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        private String nombre_tienda;

        @NotBlank(message = "La ubicacion de la tienda es obligatoria")
        private String ubicacion;

        private Date horario_apertura;

        private String politicas;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_tienda;
        private String nombre_tienda;
        private String ubicacion;
        private Date horario_apertura;
        private String politicas;
    }
}
