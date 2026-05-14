package com.ejemplo.persona.controller;

import com.ejemplo.persona.dto.PersonaDTO;
import com.ejemplo.persona.service.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaDTO.Response>> listarTodos() {
        return ResponseEntity.ok(personaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO.Response> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.buscarPorId(id));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<PersonaDTO.Response> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(personaService.buscarPorRut(rut));
    }

    @GetMapping("/genero/{generoId}")
    public ResponseEntity<List<PersonaDTO.Response>> buscarPorGenero(@PathVariable Long generoId) {
        return ResponseEntity.ok(personaService.buscarPorGenero(generoId));
    }

    @PostMapping
    public ResponseEntity<PersonaDTO.Response> crear(@Valid @RequestBody PersonaDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PersonaDTO.Request request) {
        return ResponseEntity.ok(personaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
