package com.example.rol.controller;

import com.example.rol.dto.RolDTO;
import com.example.rol.service.RolService;
import jakarta.validation.Valid;
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
    public List<RolDTO.Response> listar() {
        return rolService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO.Response> obtenerPorId(@PathVariable Integer id) {
        RolDTO.Response r = rolService.getRolById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RolDTO.Response> crear(@Valid @RequestBody RolDTO.Request request) {
        RolDTO.Response r = rolService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody RolDTO.Request request) {
        RolDTO.Response r = rolService.updateRol(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.ok().build();
    }
}