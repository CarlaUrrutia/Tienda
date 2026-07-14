package com.example.tienda.controller;

import com.example.tienda.model.Tienda;
import com.example.tienda.service.TiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/tiendas")
@Tag(name = "Tiendas", description = "Operaciones CRUD sobre tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    private void agregarLinks(Tienda t) {
        t.add(linkTo(methodOn(TiendaController.class).obtenerPorId(t.getId_tienda())).withSelfRel());
        t.add(linkTo(methodOn(TiendaController.class).listar()).withRel("tiendas"));
        t.add(linkTo(methodOn(TiendaController.class).actualizar(t.getId_tienda(), null)).withRel("actualizar"));
        t.add(linkTo(methodOn(TiendaController.class).eliminar(t.getId_tienda())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las tiendas")
    @GetMapping
    public CollectionModel<Tienda> listar() {
        var tiendas = tiendaService.getAllTiendas();
        tiendas.forEach(this::agregarLinks);
        return CollectionModel.of(tiendas, linkTo(methodOn(TiendaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una tienda por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tienda encontrada"),
            @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerPorId(@PathVariable Integer id) {
        Tienda tienda = tiendaService.getTiendaById(id);
        if (tienda == null) return ResponseEntity.notFound().build();
        agregarLinks(tienda);
        return ResponseEntity.ok(tienda);
    }

    @Operation(summary = "Crear una nueva tienda")
    @PostMapping
    public ResponseEntity<Tienda> crear(@RequestBody Tienda tienda) {
        Tienda nueva = tiendaService.save(tienda);
        if (nueva == null) return ResponseEntity.badRequest().build();
        agregarLinks(nueva);
        return ResponseEntity.ok(nueva);
    }

    @Operation(summary = "Actualizar una tienda existente")
    @PutMapping("/{id}")
    public ResponseEntity<Tienda> actualizar(@PathVariable Integer id, @RequestBody Tienda tienda) {
        Tienda actualizada = tiendaService.updateTienda(id, tienda);
        if (actualizada == null) return ResponseEntity.notFound().build();
        agregarLinks(actualizada);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una tienda")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.ok().build();
    }
}