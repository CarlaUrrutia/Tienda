package com.example.boleta.service;

import com.example.boleta.model.Boleta;
import java.util.List;

public interface BoletaService {
    List<Boleta> getAllBoletas();
    Boleta getBoletaById(Integer id);
    Boleta save(Boleta boleta);
    void delete(Integer id);
}
