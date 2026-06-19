package com.example.tienda.controller;

import com.example.tienda.dto.TiendaDTO;
import com.example.tienda.service.TiendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public List<TiendaDTO.Response> listar() {
        return tiendaService.getAllTiendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        TiendaDTO.Response r = tiendaService.getTiendaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TiendaDTO.Response> crear(@Valid @RequestBody TiendaDTO.Request request) {
        TiendaDTO.Response r = tiendaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiendaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody TiendaDTO.Request request) {
        TiendaDTO.Response r = tiendaService.updateTienda(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.ok().build();
    }
}