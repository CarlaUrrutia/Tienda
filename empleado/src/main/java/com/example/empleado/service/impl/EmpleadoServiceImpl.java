package com.example.empleado.service.impl;

import com.example.empleado.dto.EmpleadoDTO;
import com.example.empleado.client.TiendaClient;
import com.example.empleado.client.RolClient;
import com.example.empleado.model.Empleado;
import com.example.empleado.repository.EmpleadoRepository;
import com.example.empleado.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private TiendaClient tiendaClient;
    @Autowired private RolClient rolClient;

    private EmpleadoDTO.Response toResponse(Empleado e) {
        return new EmpleadoDTO.Response(
            e.getId_empleado(), e.getNombre(), e.getApellido(), e.getSueldo(),
            tiendaClient.getTiendaById(e.getId_tienda()),
            rolClient.getRolById(e.getId_rol())
        );
    }

    @Override
    public List<EmpleadoDTO.Response> getAllEmpleados() {
        return empleadoRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO.Response getEmpleadoById(Integer id) {
        List<Empleado> lista = empleadoRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public EmpleadoDTO.Response save(EmpleadoDTO.Request request) {
        Empleado e = new Empleado();
        e.setNombre(request.getNombre());
        e.setApellido(request.getApellido());
        e.setSueldo(request.getSueldo());
        e.setId_tienda(request.getId_tienda());
        e.setId_rol(request.getId_rol());
        return toResponse(empleadoRepository.save(e));
    }

    @Override
    public EmpleadoDTO.Response updateEmpleado(Integer id, EmpleadoDTO.Request request) {
        List<Empleado> lista = empleadoRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Empleado e = lista.get(0);
        e.setNombre(request.getNombre());
        e.setApellido(request.getApellido());
        e.setSueldo(request.getSueldo());
        e.setId_tienda(request.getId_tienda());
        e.setId_rol(request.getId_rol());
        return toResponse(empleadoRepository.save(e));
    }

    @Override
    public void delete(Integer id) { empleadoRepository.deleteEmpleadoById(id); }
}