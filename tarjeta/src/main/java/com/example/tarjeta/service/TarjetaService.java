package com.example.tarjeta.service;

import com.example.tarjeta.model.Tarjeta;
import java.util.List;

public interface TarjetaService {
    List<Tarjeta> getAllTarjetas();
    Tarjeta getTarjetaById(Integer id);
    Tarjeta save(Tarjeta tarjeta);
    Tarjeta updateTarjeta(Integer id, Tarjeta tarjeta);
    void delete(Integer id);
}
