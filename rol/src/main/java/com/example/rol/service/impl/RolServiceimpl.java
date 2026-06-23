package com.example.rol.service.impl;

import com.example.rol.dto.RolDTO;
import com.example.rol.model.Rol;
import com.example.rol.repository.RolRepository;
import com.example.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceimpl implements RolService {

    @Autowired private RolRepository rolRepository;

    private RolDTO.Response toResponse(Rol r) {
        return new RolDTO.Response(r.getId_rol(), r.getNombre_rol());
    }

    @Override
    public List<RolDTO.Response> getAllRoles() {
        return rolRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public RolDTO.Response getRolById(Integer id) {
        List<Rol> lista = rolRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public RolDTO.Response save(RolDTO.Request request) {
        Rol r = new Rol();
        r.setNombre_rol(request.getNombre_rol());
        return toResponse(rolRepository.save(r));
    }

    @Override
    public RolDTO.Response updateRol(Integer id, RolDTO.Request request) {
        List<Rol> lista = rolRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Rol r = lista.get(0);
        r.setNombre_rol(request.getNombre_rol());
        return toResponse(rolRepository.save(r));
    }

    @Override
    public void delete(Integer id) {
        rolRepository.deleteRolById(id);
    }
}