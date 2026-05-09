package com.example.gerente.service;
import java.util.List;
public interface InventarioService {
    List<InventarioDTO.Reponse> listarInventario();
    List<InventarioDTO.Response> buscarId(int id);
    InventarioDTO.Response crear(InventarioDTO.Request request);
    InventarioDTO.Response actualizar(int id, InventarioDTO.Request request);
    void eliminar(int id);
    
    }