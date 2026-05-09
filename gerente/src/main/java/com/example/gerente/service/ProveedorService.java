package com.example.gerente.service;
import java.util.List;
public interface OfertaService {
    List<ProveedorDTO.Reponse> listar();
    List<ProveedorDTO.Response> buscarId(int id_proveedor);
    ProveedorDTO.Response crear(ProveedorDTO.Request request);
    ProveedorDTO.Response actualizar(int id_proveedor,ProveedorDTO.Request request);
    void eliminar(int id_proveedor);
}