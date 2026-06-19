package com.example.rol.service;

import com.example.rol.dto.RolDTO;
import java.util.List;

public interface RolService {
    List<RolDTO.Response> getAllRoles();
    RolDTO.Response getRolById(Integer id);
    RolDTO.Response save(RolDTO.Request request);
    RolDTO.Response updateRol(Integer id, RolDTO.Request request);
    void delete(Integer id);
}