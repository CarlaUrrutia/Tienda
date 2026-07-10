package com.example.boleta.service;

import com.example.boleta.DTO.BoletaDTO;
import java.util.List;

public interface BoletaService {
    List<BoletaDTO.Response> getAllBoletas();
    BoletaDTO.Response getBoletaById(Integer id);
    BoletaDTO.Response save(BoletaDTO.Request request);
    void delete(Integer id);
}
