package com.ejemplo.genero.controller;

import com.ejemplo.genero.dto.GeneroDTO;
import com.ejemplo.genero.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO.Response>> listarTodos() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO.Response> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<GeneroDTO.Response> crear(@Valid @RequestBody GeneroDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody GeneroDTO.Request request) {
        return ResponseEntity.ok(generoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        generoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
