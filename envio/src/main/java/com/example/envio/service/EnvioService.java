package com.example.envio.service;

import com.example.envio.model.Envio;
import java.util.List;

public interface EnvioService {
    List<Envio> getAllEnvios();
    Envio getEnvioById(Integer id);
    Envio save(Envio envio);
    Envio updateEstado(Integer id, String estado);
    void delete(Integer id);
}
