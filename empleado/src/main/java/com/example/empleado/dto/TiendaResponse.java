package com.example.empleado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class TiendaResponse {
    private int id_tienda;
    private String nombre_tienda;
    private String ubicacion;
    private Date horario_apertura;
    private String politicas;
}
