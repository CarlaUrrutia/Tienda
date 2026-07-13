package com.example.oferta.service;

import com.example.oferta.dto.OfertaDTO;
import java.util.List;

public interface OfertaService {
    List<OfertaDTO.Response> getAllOfertas();
    OfertaDTO.Response getOfertaById(Integer id);
    OfertaDTO.Response save(OfertaDTO.Request request);
    OfertaDTO.Response updateOferta(Integer id, OfertaDTO.Request request);
    void delete(Integer id);
}
