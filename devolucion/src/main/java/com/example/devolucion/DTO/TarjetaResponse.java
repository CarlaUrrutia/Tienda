package com.example.devolucion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TarjetaResponse {
    private int id_tarjeta;
    private char tipo;
    private int id_cliente;
}
