package com.example.boleta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class VentaResponse {
    private int id_venta;
    private Date fecha_venta;
    private int id_cliente;
    private int id_empleado;
}
