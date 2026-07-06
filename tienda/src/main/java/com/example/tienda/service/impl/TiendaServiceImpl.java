package com.example.tienda.service.impl;

import com.example.tienda.dto.TiendaDTO;
import com.example.tienda.model.Tienda;
import com.example.tienda.repository.TiendaRepository;
import com.example.tienda.service.TiendaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired private TiendaRepository tiendaRepository;

    private TiendaDTO.Response toResponse(Tienda t) {
        return new TiendaDTO.Response(
            t.getId_tienda(), t.getNombre_tienda(), t.getUbicacion(),
            t.getHorario_apertura(), t.getPoliticas()
        );
    }

    @Override
    public List<TiendaDTO.Response> getAllTiendas() {
        return tiendaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TiendaDTO.Response getTiendaById(Integer id) {
        List<Tienda> lista = tiendaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Tienda con id " + id + " no encontrada");
        }
        return toResponse(lista.get(0));
    }

    @Override
    public TiendaDTO.Response save(TiendaDTO.Request request) {
        Tienda t = new Tienda();
        t.setNombre_tienda(request.getNombre_tienda());
        t.setUbicacion(request.getUbicacion());
        t.setHorario_apertura(request.getHorario_apertura());
        t.setPoliticas(request.getPoliticas());
        return toResponse(tiendaRepository.save(t));
    }

    @Override
    public TiendaDTO.Response updateTienda(Integer id, TiendaDTO.Request request) {
        List<Tienda> lista = tiendaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Tienda con id " + id + " no encontrada para actualizar");
        }
        Tienda t = lista.get(0);
        t.setNombre_tienda(request.getNombre_tienda());
        t.setUbicacion(request.getUbicacion());
        t.setHorario_apertura(request.getHorario_apertura());
        t.setPoliticas(request.getPoliticas());
        return toResponse(tiendaRepository.save(t));
    }

    @Override
    public void delete(Integer id) {
        getTiendaById(id); // valida existencia antes de borrar
        tiendaRepository.deleteTiendaById(id);
    }
}