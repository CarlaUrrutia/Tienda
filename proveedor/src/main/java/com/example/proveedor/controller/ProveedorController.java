package com.example.proveedor.controller;

import com.example.proveedor.dto.ProveedorDTO;
import com.example.proveedor.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorDTO.Response> listar() { return proveedorService.getAllProveedores(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO.Response> obtenerPorId(@PathVariable Integer id) {
        ProveedorDTO.Response r = proveedorService.getProveedorById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO.Response> crear(@Valid @RequestBody ProveedorDTO.Request request) {
        ProveedorDTO.Response r = proveedorService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody ProveedorDTO.Request request) {
        ProveedorDTO.Response r = proveedorService.updateProveedor(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.delete(id);
        return ResponseEntity.ok().build();
    }
}