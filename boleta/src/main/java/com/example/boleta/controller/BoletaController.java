package com.example.boleta.controller;

import com.example.boleta.dto.BoletaDTO;
import com.example.boleta.service.BoletaService;
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

@Tag(name = "Boletas", description = "Gestión de boletas")
@RestController
@RequestMapping("/api/boletas")
@Tag(name = "Boletas", description = "Operaciones CRUD sobre boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    private void agregarLinks(BoletaDTO.Response r) {
        r.add(linkTo(methodOn(BoletaController.class).obtenerPorId(r.getId_boleta())).withSelfRel());
        r.add(linkTo(methodOn(BoletaController.class).listar()).withRel("boletas"));
        r.add(linkTo(methodOn(BoletaController.class).eliminar(r.getId_boleta())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las boletas")
    @GetMapping
    public CollectionModel<BoletaDTO.Response> listar() {
        var boletas = boletaService.getAllBoletas();
        boletas.forEach(this::agregarLinks);
        return CollectionModel.of(boletas, linkTo(methodOn(BoletaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una boleta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Boleta encontrada"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BoletaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        BoletaDTO.Response boleta = boletaService.getBoletaById(id);
        if (boleta == null) return ResponseEntity.notFound().build();
        agregarLinks(boleta);
        return ResponseEntity.ok(boleta);
    }

    @Operation(summary = "Crear una nueva boleta")
    @PostMapping
    public ResponseEntity<BoletaDTO.Response> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        if (nueva == null) return ResponseEntity.badRequest().build();
        agregarLinks(nueva);
        return ResponseEntity.ok(nueva);
    }

    @Operation(summary = "Eliminar una boleta")
    @Operation(summary = "Eliminar una boleta por ID")
    @ApiResponse(responseCode = "200", description = "Boleta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boletaService.delete(id);
        return ResponseEntity.ok().build();
    }
}