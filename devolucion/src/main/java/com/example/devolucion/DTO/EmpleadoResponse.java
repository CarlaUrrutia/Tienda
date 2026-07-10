package com.example.devolucion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmpleadoResponse {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private float sueldo;
    private int id_tienda;
}
