package com.example.envio.controller;

import com.example.envio.model.Envio;
import com.example.envio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listar() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable Integer id) {
        Envio envio = envioService.getEnvioById(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Envio> crear(@RequestBody Envio envio) {
        Envio nuevo = envioService.save(envio);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Integer id, @RequestParam String estado) {
        Envio actualizado = envioService.updateEstado(id, estado);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
