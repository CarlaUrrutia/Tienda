package com.example.envio.controller;

import com.example.envio.dto.EnvioDTO;
import com.example.envio.service.EnvioService;
import jakarta.validation.Valid;
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
    public List<EnvioDTO.Response> listar() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EnvioDTO.Response r = envioService.getEnvioById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnvioDTO.Response> crear(@Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.updateEnvio(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
