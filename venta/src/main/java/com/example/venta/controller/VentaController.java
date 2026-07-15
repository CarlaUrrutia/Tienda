package com.example.venta.controller;

import com.example.venta.DTO.VentaDTO;
import com.example.venta.service.VentaService;
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
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "Operaciones CRUD sobre ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    private void agregarLinks(VentaDTO.Response r) {
        r.add(linkTo(methodOn(VentaController.class).obtenerPorId(r.getId_venta())).withSelfRel());
        r.add(linkTo(methodOn(VentaController.class).listar()).withRel("ventas"));
        r.add(linkTo(methodOn(VentaController.class).actualizar(r.getId_venta(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(VentaController.class).eliminar(r.getId_venta())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las ventas")
    @GetMapping
    public CollectionModel<VentaDTO.Response> listar() {
        var ventas = ventaService.getAllVentas();
        ventas.forEach(this::agregarLinks);
        return CollectionModel.of(ventas, linkTo(methodOn(VentaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una venta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        VentaDTO.Response venta = ventaService.getVentaById(id);
        if (venta == null) return ResponseEntity.notFound().build();
        agregarLinks(venta);
        return ResponseEntity.ok(venta);
    }

    @Operation(summary = "Crear una nueva venta")
    @PostMapping
    public ResponseEntity<VentaDTO.Response> crear(@Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response nueva = ventaService.save(request);
        if (nueva == null) return ResponseEntity.badRequest().build();
        agregarLinks(nueva);
        return ResponseEntity.ok(nueva);
    }

    @Operation(summary = "Actualizar una venta existente")
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response actualizada = ventaService.updateVenta(id, request);
        if (actualizada == null) return ResponseEntity.notFound().build();
        agregarLinks(actualizada);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.ok().build();
    }
}