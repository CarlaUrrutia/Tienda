package com.example.oferta.service.impl;

import com.example.oferta.model.Oferta;
import com.example.oferta.repository.OfertaRepository;
import com.example.oferta.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public List<Oferta> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    @Override
    public Oferta getOfertaById(Integer id) {
        List<Oferta> lista = ofertaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Oferta save(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    public Oferta updateOferta(Integer id, Oferta oferta) {
        Oferta existente = getOfertaById(id);
        if (existente != null) {
            existente.setDescripcion(oferta.getDescripcion());
            existente.setDescuento(oferta.getDescuento());
            existente.setId_producto(oferta.getId_producto());
            return ofertaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        ofertaRepository.deleteOfertaById(id);
    }
}
