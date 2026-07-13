package com.example.inventario.controller;

import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.service.InventarioService;
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

@Tag(name = "Inventario", description = "Gestión de inventario")
@RestController
@RequestMapping("/api/inventarios")
@Tag(name = "Inventarios", description = "Operaciones CRUD sobre inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    private void agregarLinks(InventarioDTO.Response r) {
        r.add(linkTo(methodOn(InventarioController.class).obtenerPorId(r.getId_inventario())).withSelfRel());
        r.add(linkTo(methodOn(InventarioController.class).listar()).withRel("inventarios"));
        r.add(linkTo(methodOn(InventarioController.class).actualizar(r.getId_inventario(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(InventarioController.class).eliminar(r.getId_inventario())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los inventarios")
    @GetMapping
    public CollectionModel<InventarioDTO.Response> listar() {
        var inventarios = inventarioService.getAllInventarios();
        inventarios.forEach(this::agregarLinks);
        return CollectionModel.of(inventarios, linkTo(methodOn(InventarioController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un inventario por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        InventarioDTO.Response r = inventarioService.getInventarioById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear un nuevo inventario")
    @PostMapping
    public ResponseEntity<InventarioDTO.Response> crear(@Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar un inventario existente")
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.updateInventario(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar un inventario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}