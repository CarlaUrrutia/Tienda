package com.example.empleado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class RolResponse {
    private int id_rol;
    private String nombre_rol;
}
