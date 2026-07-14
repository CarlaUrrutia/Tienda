package com.example.devolucion.controller;

import com.example.devolucion.DTO.DevolucionDTO;
import com.example.devolucion.service.DevolucionService;
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
@RequestMapping("/api/devoluciones")
@Tag(name = "Devoluciones", description = "Operaciones CRUD sobre devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    private void agregarLinks(DevolucionDTO.Response r) {
        r.add(linkTo(methodOn(DevolucionController.class).obtenerPorId(r.getId_devolucion())).withSelfRel());
        r.add(linkTo(methodOn(DevolucionController.class).listar()).withRel("devoluciones"));
        r.add(linkTo(methodOn(DevolucionController.class).actualizar(r.getId_devolucion(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(DevolucionController.class).eliminar(r.getId_devolucion())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las devoluciones")
    @GetMapping
    public CollectionModel<DevolucionDTO.Response> listar() {
        var devoluciones = devolucionService.getAllDevoluciones();
        devoluciones.forEach(this::agregarLinks);
        return CollectionModel.of(devoluciones, linkTo(methodOn(DevolucionController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una devolución por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Devolución encontrada"),
            @ApiResponse(responseCode = "404", description = "Devolución no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DevolucionDTO.Response r = devolucionService.getDevolucionById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear una nueva devolución")
    @PostMapping
    public ResponseEntity<DevolucionDTO.Response> crear(@Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar una devolución existente")
    @PutMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.updateDevolucion(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar una devolución")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.ok().build();
    }
}