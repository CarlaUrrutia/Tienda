package com.example.inventario.service;

import com.example.inventario.model.Inventario;
import java.util.List;

public interface InventarioService {
    List<Inventario> getAllInventarios();
    Inventario getInventarioById(Integer id);
    Inventario save(Inventario inventario);
    Inventario updateInventario(Integer id, Inventario inventario);
    void delete(Integer id);
}
