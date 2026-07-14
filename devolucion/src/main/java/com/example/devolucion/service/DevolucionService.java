package com.example.devolucion.service;

import com.example.devolucion.DTO.DevolucionDTO;
import java.util.List;

public interface DevolucionService {
    List<DevolucionDTO.Response> getAllDevoluciones();
    DevolucionDTO.Response getDevolucionById(Integer id);
    DevolucionDTO.Response save(DevolucionDTO.Request request);
    DevolucionDTO.Response updateDevolucion(Integer id, DevolucionDTO.Request request);
    void delete(Integer id);
}
