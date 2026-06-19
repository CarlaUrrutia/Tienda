package com.example.factura.controller;

import com.example.factura.DTO.FacturaDTO;
import com.example.factura.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Facturas", description = "Gestión de facturas")
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Operation(summary = "Listar todas las facturas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<FacturaDTO.Response> listar() {
        return facturaService.getAllFacturas();
    }

    @Operation(summary = "Obtener factura por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Factura encontrada"),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        FacturaDTO.Response r = facturaService.getFacturaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva factura")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Factura creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<FacturaDTO.Response> crear(@Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una factura existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Factura actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.updateFactura(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una factura por ID")
    @ApiResponse(responseCode = "200", description = "Factura eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
