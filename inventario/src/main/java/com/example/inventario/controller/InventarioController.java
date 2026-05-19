package com.example.inventario.controller;

import com.example.inventario.model.Inventario;
import com.example.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.getAllInventarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Integer id) {
        Inventario inventario = inventarioService.getInventarioById(id);
        return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.save(inventario);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Integer id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.updateInventario(id, inventario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
