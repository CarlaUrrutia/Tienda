package com.example.oferta.controller;

import com.example.oferta.model.Oferta;
import com.example.oferta.service.OfertaService;
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
    public List<Oferta> listar() {
        return ofertaService.getAllOfertas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> obtenerPorId(@PathVariable Integer id) {
        Oferta oferta = ofertaService.getOfertaById(id);
        return oferta != null ? ResponseEntity.ok(oferta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Oferta> crear(@RequestBody Oferta oferta) {
        Oferta nueva = ofertaService.save(oferta);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oferta> actualizar(@PathVariable Integer id, @RequestBody Oferta oferta) {
        Oferta actualizada = ofertaService.updateOferta(id, oferta);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
