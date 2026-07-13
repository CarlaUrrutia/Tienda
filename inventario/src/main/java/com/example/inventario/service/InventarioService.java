package com.example.inventario.service;

import com.example.inventario.dto.InventarioDTO;
import java.util.List;

public interface InventarioService {
    List<InventarioDTO.Response> getAllInventarios();
    InventarioDTO.Response getInventarioById(Integer id);
    InventarioDTO.Response save(InventarioDTO.Request request);
    InventarioDTO.Response updateInventario(Integer id, InventarioDTO.Request request);
    void delete(Integer id);
}
