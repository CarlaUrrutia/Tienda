package com.example.tarjeta.service.impl;

import com.example.tarjeta.model.Tarjeta;
import com.example.tarjeta.repository.TarjetaRepository;
import com.example.tarjeta.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public List<Tarjeta> getAllTarjetas() {
        return tarjetaRepository.findAll();
    }

    @Override
    public Tarjeta getTarjetaById(Integer id) {
        List<Tarjeta> lista = tarjetaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public Tarjeta updateTarjeta(Integer id, Tarjeta tarjeta) {
        Tarjeta existente = getTarjetaById(id);
        if (existente != null) {
            existente.setTipo(tarjeta.getTipo());
            existente.setId_cliente(tarjeta.getId_cliente());
            return tarjetaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        tarjetaRepository.deleteTarjetaById(id);
    }
}
