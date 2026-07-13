package com.example.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProveedorResponse {
    private int id_proveedor;
    private String nombre;
    private String contacto;
}
