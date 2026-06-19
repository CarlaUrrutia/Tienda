package com.example.rol.service;

import com.example.rol.model.Rol;
import java.util.List;

public interface RolService {
    List<Rol> getAllRoles();
    Rol getRolById(Integer id);
    Rol save(Rol rol);
    void delete(Integer id);
}
