package com.example.gerente.service;
import java.util.List;
public interface TarjetaService {
    List<TarjetaDTO.Reponse> listar();
    List<TarjetaDTO.Response> buscarId(int id_tarjeta);
    TarjetaDTO.Response crear(TarjetaDTO.Request request);
    TarjetaDTO.Response actualizar(int id_tarjeta,TarjetaDTO.Request request);
    void eliminar(int id_tarjeta);
}