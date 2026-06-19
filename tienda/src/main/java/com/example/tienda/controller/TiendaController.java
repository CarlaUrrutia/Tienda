package com.example.tienda.controller;

import com.example.tienda.model.Tienda;
import com.example.tienda.service.TiendaService;
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
    public List<Tienda> listar() {
        return tiendaService.getAllTiendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerPorId(@PathVariable Integer id) {
        Tienda tienda = tiendaService.getTiendaById(id);
        return tienda != null ? ResponseEntity.ok(tienda) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tienda> crear(@RequestBody Tienda tienda) {
        Tienda nueva = tiendaService.save(tienda);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tienda> actualizar(@PathVariable Integer id, @RequestBody Tienda tienda) {
        Tienda actualizada = tiendaService.updateTienda(id, tienda);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
