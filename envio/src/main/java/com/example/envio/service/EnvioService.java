package com.example.envio.service;

import com.example.envio.DTO.EnvioDTO;
import java.util.List;

public interface EnvioService {
    List<EnvioDTO.Response> getAllEnvios();
    EnvioDTO.Response getEnvioById(Integer id);
    EnvioDTO.Response save(EnvioDTO.Request request);
    EnvioDTO.Response updateEnvio(Integer id, EnvioDTO.Request request);
    void delete(Integer id);
}
