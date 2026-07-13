package com.example.oferta.controller;

import com.example.oferta.dto.OfertaDTO;
import com.example.oferta.service.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ofertas", description = "Gestión de ofertas y descuentos")
@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @Operation(summary = "Listar todas las ofertas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<OfertaDTO.Response> listar() {
        return ofertaService.getAllOfertas();
    }

    @Operation(summary = "Obtener oferta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Oferta encontrada"),
        @ApiResponse(responseCode = "404", description = "Oferta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        OfertaDTO.Response r = ofertaService.getOfertaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva oferta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Oferta creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<OfertaDTO.Response> crear(@Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una oferta existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Oferta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Oferta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.updateOferta(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una oferta por ID")
    @ApiResponse(responseCode = "200", description = "Oferta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
