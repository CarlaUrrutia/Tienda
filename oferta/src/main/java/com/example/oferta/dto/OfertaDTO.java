package com.example.oferta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import java.sql.Date;

public class OfertaDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @Min(value = 0, message = "El id de la oferta no puede ser negativo")
        private int id_oferta;
        @NotBlank(message = "La descripcion es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La descripcion debe contener al menos 2 palabras")
        private String descripcion;
        @Min(value = 0, message = "El descuento no puede ser negativo")
        private int descuento;
        @NotNull(message = "La fecha de inicio es obligatoria")
        private Date fecha_inicio;
        @NotNull(message = "La fecha de fin es obligatoria")
        private Date fecha_fin;
        @Min(value = 1, message = "El id_producto es obligatorio")
        private int id_producto;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Response extends RepresentationModel<Response> {
        private int id_oferta;
        private String descripcion;
        private int descuento;
        private Date fecha_inicio;
        private Date fecha_fin;
        private ProductoResponse producto;
    }
}