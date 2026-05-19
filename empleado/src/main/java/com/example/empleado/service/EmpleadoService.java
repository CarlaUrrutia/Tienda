package com.example.empleado.service;

import com.example.empleado.model.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> getAllEmpleados();
    Empleado getEmpleadoById(Integer id);
    Empleado save(Empleado empleado);
    Empleado updateEmpleado(Integer id, Empleado empleado);
    void delete(Integer id);
}
