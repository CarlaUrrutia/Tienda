package com.example.empleado.service.impl;

import com.example.empleado.model.Empleado;
import com.example.empleado.repository.EmpleadoRepository;
import com.example.empleado.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        List<Empleado> lista = empleadoRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {
        Empleado existente = getEmpleadoById(id);
        if (existente != null) {
            existente.setNombre(empleado.getNombre());
            existente.setApellido(empleado.getApellido());
            existente.setSueldo(empleado.getSueldo());
            existente.setId_tienda(empleado.getId_tienda());
            return empleadoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        empleadoRepository.deleteEmpleadoById(id);
    }
}
