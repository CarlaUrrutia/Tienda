package com.example.gerente.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OfertaDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id de la oferta no puede ser negativo")
        private int id_oferta;

        @NotBlank(message = "La descripcion es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La descripcion debe contener al menos 2 palabras")
        private String descripcion;

        @Min(value = 0, message = "El descuento de la oferta no puede ser negativo")
        private int descuento;  
    }

    //TAmbien me da error con el data y el AllArgsConstructor y la clave foranea ProductoDTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_oferta;
        private String descripcion;
        private int descuento;
        private ProductoTDO producto; // clave foranea
    }
}
