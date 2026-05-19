package com.example.boleta.service.impl;

import com.example.boleta.model.Boleta;
import com.example.boleta.repository.BoletaRepository;
import com.example.boleta.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoletaServiceImpl implements BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Override
    public List<Boleta> getAllBoletas() {
        return boletaRepository.findAll();
    }

    @Override
    public Boleta getBoletaById(Integer id) {
        List<Boleta> lista = boletaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Boleta save(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    @Override
    public void delete(Integer id) {
        boletaRepository.deleteBoletaById(id);
    }
}
