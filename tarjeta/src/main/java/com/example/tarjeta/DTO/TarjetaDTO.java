package com.example.tarjeta.DTO;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TarjetaDTO {
     @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la tarjeta no puede ser negativo")
        private int id_tarjeta;
        
    }

    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_tarjeta;
        private ClienteDTO cliente;
    }
}
