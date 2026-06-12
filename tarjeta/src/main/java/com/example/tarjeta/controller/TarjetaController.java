package com.example.tarjeta.controller;

import com.example.tarjeta.DTO.TarjetaDTO;
import com.example.tarjeta.service.TarjetaService;
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
@RequestMapping("/api/tarjetas")
@Tag(name = "Tarjetas", description = "Operaciones CRUD sobre tarjetas")
public class TarjetaController {
    @Autowired private TarjetaService tarjetaService;

    private void agregarLinks(TarjetaDTO.Response r) {
        r.add(linkTo(methodOn(TarjetaController.class).obtenerPorId(r.getId_tarjeta())).withSelfRel());
        r.add(linkTo(methodOn(TarjetaController.class).listar()).withRel("tarjetas"));
        r.add(linkTo(methodOn(TarjetaController.class).actualizar(r.getId_tarjeta(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(TarjetaController.class).eliminar(r.getId_tarjeta())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las tarjetas")
    @GetMapping
    public CollectionModel<TarjetaDTO.Response> listar() {
        var tarjetas = tarjetaService.getAllTarjetas();
        tarjetas.forEach(this::agregarLinks);
        return CollectionModel.of(tarjetas, linkTo(methodOn(TarjetaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una tarjeta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarjeta encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        TarjetaDTO.Response r = tarjetaService.getTarjetaById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear una nueva tarjeta")
    @PostMapping
    public ResponseEntity<TarjetaDTO.Response> crear(@Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar una tarjeta existente")
    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.updateTarjeta(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar una tarjeta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.ok().build();
    }
}