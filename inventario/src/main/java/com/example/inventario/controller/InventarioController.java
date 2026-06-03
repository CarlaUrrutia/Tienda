package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.model.Factura;
import com.example.gerente.model.Inventario;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<InventarioDTO.Response> listar() {
        return inventarioService.getAllInventarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        InventarioDTO.Response r = inventarioService.getInventarioById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventarioDTO.Response> crear(@Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.updateInventario(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
