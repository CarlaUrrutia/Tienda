package com.example.oferta.service;

import com.example.oferta.model.Oferta;
import java.util.List;

public interface OfertaService {
    List<Oferta> getAllOfertas();
    Oferta getOfertaById(Integer id);
    Oferta save(Oferta oferta);
    Oferta updateOferta(Integer id, Oferta oferta);
    void delete(Integer id);
}
