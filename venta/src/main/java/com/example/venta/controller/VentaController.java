package com.example.venta.controller;

import com.example.venta.dto.VentaDTO;
import com.example.venta.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ventas", description = "Gestión de ventas")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Listar todas las ventas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<VentaDTO.Response> listar() {
        return ventaService.getAllVentas();
    }

    @Operation(summary = "Obtener venta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta encontrada"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        VentaDTO.Response venta = ventaService.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva venta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<VentaDTO.Response> crear(@Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response nueva = ventaService.save(request);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una venta existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response actualizada = ventaService.updateVenta(id, request);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
