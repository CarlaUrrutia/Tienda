package com.example.tarjeta.service;

import com.example.tarjeta.DTO.TarjetaDTO;
import java.util.List;

public interface TarjetaService {
    List<TarjetaDTO.Response> getAllTarjetas();
    TarjetaDTO.Response getTarjetaById(Integer id);
    TarjetaDTO.Response save(TarjetaDTO.Request request);
    TarjetaDTO.Response updateTarjeta(Integer id, TarjetaDTO.Request request);
    void delete(Integer id);
}
