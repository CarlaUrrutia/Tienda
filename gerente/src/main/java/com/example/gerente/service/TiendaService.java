package com.example.gerente.service;
import java.util.List;
public interface TiendaService {
    List<TiendaDTO.Reponse> listar();
    List<TiendaDTO.Response> buscarId(int id_tienda);
    TiendaDTO.Response crear(TiendaDTO.Request request);
    TiendaDTO.Response actualizar(int id_tienda,TiendaDTO.Request request);
    void eliminar(int id_tienda);
}