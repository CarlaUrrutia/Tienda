package com.example.empleado.service;

import com.example.empleado.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    List<EmpleadoDTO.Response> getAllEmpleados();
    EmpleadoDTO.Response getEmpleadoById(Integer id);
    EmpleadoDTO.Response save(EmpleadoDTO.Request request);
    EmpleadoDTO.Response updateEmpleado(Integer id, EmpleadoDTO.Request request);
    void delete(Integer id);
}
