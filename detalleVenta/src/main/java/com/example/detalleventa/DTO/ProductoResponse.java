package com.example.detalleVenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductoResponse {
    private int id_producto;
    private String nombre;
    private int precio_venta;
    private int id_proveedor;
}
