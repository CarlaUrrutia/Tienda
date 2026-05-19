package com.example.envio.service.impl;

import com.example.envio.model.Envio;
import com.example.envio.repository.EnvioRepository;
import com.example.envio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    @Override
    public Envio getEnvioById(Integer id) {
        List<Envio> lista = envioRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public Envio updateEstado(Integer id, String estado) {
        Envio existente = getEnvioById(id);
        if (existente != null) {
            existente.setEstado(estado);
            return envioRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        envioRepository.deleteEnvioById(id);
    }
}
