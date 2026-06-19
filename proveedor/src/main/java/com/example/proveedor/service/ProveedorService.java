package com.example.proveedor.service;

import com.example.proveedor.dto.ProveedorDTO;
import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO.Response> getAllProveedores();
    ProveedorDTO.Response getProveedorById(Integer id);
    ProveedorDTO.Response save(ProveedorDTO.Request request);
    ProveedorDTO.Response updateProveedor(Integer id, ProveedorDTO.Request request);
    void delete(Integer id);
}