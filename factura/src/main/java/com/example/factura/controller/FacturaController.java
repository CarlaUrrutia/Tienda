package com.example.factura.controller;

import com.example.factura.DTO.FacturaDTO;
import com.example.factura.service.FacturaService;
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
@RequestMapping("/api/facturas")
@Tag(name = "Facturas", description = "Operaciones CRUD sobre facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    private void agregarLinks(FacturaDTO.Response r) {
        r.add(linkTo(methodOn(FacturaController.class).obtenerPorId(r.getId_factura())).withSelfRel());
        r.add(linkTo(methodOn(FacturaController.class).listar()).withRel("facturas"));
        r.add(linkTo(methodOn(FacturaController.class).actualizar(r.getId_factura(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(FacturaController.class).eliminar(r.getId_factura())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todas las facturas")
    @GetMapping
    public CollectionModel<FacturaDTO.Response> listar() {
        var facturas = facturaService.getAllFacturas();
        facturas.forEach(this::agregarLinks);
        return CollectionModel.of(facturas, linkTo(methodOn(FacturaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener una factura por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        FacturaDTO.Response r = facturaService.getFacturaById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear una nueva factura")
    @PostMapping
    public ResponseEntity<FacturaDTO.Response> crear(@Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar una factura existente")
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.updateFactura(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar una factura")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.ok().build();
    }
}