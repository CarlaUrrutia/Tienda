package com.example.tienda.service.impl;

import com.example.tienda.model.Tienda;
import com.example.tienda.repository.TiendaRepository;
import com.example.tienda.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAll();
    }

    @Override
    public Tienda getTiendaById(Integer id) {
        List<Tienda> lista = tiendaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Tienda save(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    @Override
    public Tienda updateTienda(Integer id, Tienda tienda) {
        Tienda existente = getTiendaById(id);
        if (existente != null) {
            existente.setNombre_tienda(tienda.getNombre_tienda());
            existente.setUbicacion(tienda.getUbicacion());
            existente.setHorario_apertura(tienda.getHorario_apertura());
            existente.setPoliticas(tienda.getPoliticas());
            return tiendaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        tiendaRepository.deleteTiendaById(id);
    }
}
