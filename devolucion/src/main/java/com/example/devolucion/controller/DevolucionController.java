package com.example.devolucion.controller;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public List<Devolucion> listar() {
        return devolucionService.getAllDevoluciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> obtenerPorId(@PathVariable Integer id) {
        Devolucion devolucion = devolucionService.getDevolucionById(id);
        return devolucion != null ? ResponseEntity.ok(devolucion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Devolucion> crear(@RequestBody Devolucion devolucion) {
        Devolucion nueva = devolucionService.save(devolucion);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizar(@PathVariable Integer id, @RequestBody Devolucion devolucion) {
        Devolucion actualizada = devolucionService.updateDevolucion(id, devolucion);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
