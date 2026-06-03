package com.example.tienda.service;

import com.example.tienda.model.Tienda;
import java.util.List;

public interface TiendaService {
    List<Tienda> getAllTiendas();
    Tienda getTiendaById(Integer id);
    Tienda save(Tienda tienda);
    Tienda updateTienda(Integer id, Tienda tienda);
    void delete(Integer id);
}
