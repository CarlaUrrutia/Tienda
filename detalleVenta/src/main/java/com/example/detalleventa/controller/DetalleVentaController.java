package com.example.detalleventa.controller;

import com.example.detalleventa.dto.DetalleVentaDTO;
import com.example.detalleventa.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Detalle de Ventas", description = "Gestión del detalle de ventas")
@RestController
@RequestMapping("/api/detalles")
@Tag(name = "Detalles de Venta", description = "Operaciones CRUD sobre detalles de venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    private void agregarLinks(DetalleVentaDTO.Response r) {
        r.add(linkTo(methodOn(DetalleVentaController.class).obtenerPorId(r.getId_detalle())).withSelfRel());
        r.add(linkTo(methodOn(DetalleVentaController.class).listar()).withRel("detalles"));
        r.add(linkTo(methodOn(DetalleVentaController.class).actualizar(r.getId_detalle(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(DetalleVentaController.class).eliminar(r.getId_detalle())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los detalles de venta")
    @Operation(summary = "Listar todos los detalles de venta")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public CollectionModel<DetalleVentaDTO.Response> listar() {
        var detalles = detalleVentaService.getAllDetalles();
        detalles.forEach(this::agregarLinks);
        return CollectionModel.of(detalles, linkTo(methodOn(DetalleVentaController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un detalle de venta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    @Operation(summary = "Obtener detalle de venta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle encontrado"),
        @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DetalleVentaDTO.Response r = detalleVentaService.getDetalleById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Obtener los detalles de una venta específica")
    @Operation(summary = "Obtener detalles por ID de venta")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping("/venta/{idVenta}")
    public List<DetalleVentaDTO.Response> obtenerPorVenta(@PathVariable Integer idVenta) {
        var detalles = detalleVentaService.getDetallesByVenta(idVenta);
        detalles.forEach(this::agregarLinks);
        return detalles;
    }

    @Operation(summary = "Crear un nuevo detalle de venta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<DetalleVentaDTO.Response> crear(@Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar un detalle de venta existente")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.updateDetalle(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar un detalle de venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}