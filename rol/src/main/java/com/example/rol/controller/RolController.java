package com.example.rol.controller;

import com.example.rol.model.Rol;
import com.example.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> listar() {
        return rolService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Integer id) {
        Rol rol = rolService.getRolById(id);
        return rol != null ? ResponseEntity.ok(rol) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Rol> crear(@RequestBody Rol rol) {
        Rol nuevo = rolService.save(rol);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.ok().build();
    }
}
