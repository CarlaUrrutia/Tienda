package com.example.oferta.controller;

import com.example.oferta.dto.OfertaDTO;
import com.example.oferta.service.OfertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public List<OfertaDTO.Response> listar() {
        return ofertaService.getAllOfertas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        OfertaDTO.Response r = ofertaService.getOfertaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OfertaDTO.Response> crear(@Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.updateOferta(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.ok().build();
    }
}