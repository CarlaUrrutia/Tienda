package com.example.gerente.service;
import java.util.List;
public interface VentaService {
    List<VentaDTO.Reponse> listar();
    List<VentaDTO.Response> buscarId(int id_venta);
    VentaDTO.Response crear(VentaDTO.Request request);
    VentaDTO.Response actualizar(int id_venta,VentaDTO.Request request);
    void eliminar(int id_venta);
}