package com.example.proveedor.service;

import com.example.proveedor.model.Proveedor;
import java.util.List;

public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Proveedor getProveedorById(Integer id);
    Proveedor save(Proveedor proveedor);
    Proveedor updateProveedor(Integer id, Proveedor proveedor);
    void delete(Integer id);
}
