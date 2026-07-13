package com.example.detalleventa.controller;

import com.example.detalleventa.dto.DetalleVentaDTO;
import com.example.detalleventa.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Detalle de Ventas", description = "Gestión del detalle de ventas")
@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Operation(summary = "Listar todos los detalles de venta")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<DetalleVentaDTO.Response> listar() {
        return detalleVentaService.getAllDetalles();
    }

    @Operation(summary = "Obtener detalle de venta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle encontrado"),
        @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DetalleVentaDTO.Response r = detalleVentaService.getDetalleById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener detalles por ID de venta")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping("/venta/{idVenta}")
    public List<DetalleVentaDTO.Response> obtenerPorVenta(@PathVariable Integer idVenta) {
        return detalleVentaService.getDetallesByVenta(idVenta);
    }

    @Operation(summary = "Crear un nuevo detalle de venta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<DetalleVentaDTO.Response> crear(@Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un detalle de venta existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.updateDetalle(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un detalle de venta por ID")
    @ApiResponse(responseCode = "200", description = "Detalle eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
