package com.example.tienda.service;

import com.example.tienda.dto.TiendaDTO;
import java.util.List;

public interface TiendaService {
    List<TiendaDTO.Response> getAllTiendas();
    TiendaDTO.Response getTiendaById(Integer id);
    TiendaDTO.Response save(TiendaDTO.Request request);
    TiendaDTO.Response updateTienda(Integer id, TiendaDTO.Request request);
    void delete(Integer id);
}