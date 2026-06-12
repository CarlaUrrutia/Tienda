package com.example.oferta.controller;

import com.example.oferta.DTO.OfertaDTO;
import com.example.oferta.service.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ofertas")
@Tag(name = "Ofertas", description = "Operaciones CRUD sobre ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    private void agregarLinks(OfertaDTO.Response r) {
        r.add(linkTo(methodOn(OfertaController.class).obtenerPorId(r.getId_oferta())).withSelfRel());
        r.add(linkTo(methodOn(OfertaController.class).listar()).withRel("ofertas"));
        r.add(linkTo(methodOn(OfertaController.class).actualizar(r.getId_oferta(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(OfertaController.class).eliminar(r.getId_oferta())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las ofertas")
    @GetMapping
    public CollectionModel<OfertaDTO.Response> listar() {
        var ofertas = ofertaService.getAllOfertas();
        ofertas.forEach(this::agregarLinks);
        return CollectionModel.of(ofertas, linkTo(methodOn(OfertaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una oferta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Oferta encontrada"),
            @ApiResponse(responseCode = "404", description = "Oferta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        OfertaDTO.Response r = ofertaService.getOfertaById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear una nueva oferta")
    @PostMapping
    public ResponseEntity<OfertaDTO.Response> crear(@Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar una oferta existente")
    @PutMapping("/{id}")
    public ResponseEntity<OfertaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response r = ofertaService.updateOferta(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar una oferta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.ok().build();
    }
}