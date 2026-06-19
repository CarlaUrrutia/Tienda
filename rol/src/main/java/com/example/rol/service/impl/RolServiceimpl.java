package com.example.rol.service.impl;

import com.example.rol.model.Rol;
import com.example.rol.repository.RolRepository;
import com.example.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getRolById(Integer id) {
        List<Rol> lista = rolRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void delete(Integer id) {
        rolRepository.deleteRolById(id);
    }
}
