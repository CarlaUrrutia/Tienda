package com.example.tarjeta.controller;

import com.example.tarjeta.model.Tarjeta;
import com.example.tarjeta.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping
    public List<Tarjeta> listar() {
        return tarjetaService.getAllTarjetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> obtenerPorId(@PathVariable Integer id) {
        Tarjeta tarjeta = tarjetaService.getTarjetaById(id);
        return tarjeta != null ? ResponseEntity.ok(tarjeta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tarjeta> crear(@RequestBody Tarjeta tarjeta) {
        Tarjeta nueva = tarjetaService.save(tarjeta);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> actualizar(@PathVariable Integer id, @RequestBody Tarjeta tarjeta) {
        Tarjeta actualizada = tarjetaService.updateTarjeta(id, tarjeta);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
