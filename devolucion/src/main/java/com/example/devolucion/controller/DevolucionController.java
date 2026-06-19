package com.example.devolucion.controller;

import com.example.devolucion.DTO.DevolucionDTO;
import com.example.devolucion.service.DevolucionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Devoluciones", description = "Gestión de devoluciones")
@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @Operation(summary = "Listar todas las devoluciones")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<DevolucionDTO.Response> listar() {
        return devolucionService.getAllDevoluciones();
    }

    @Operation(summary = "Obtener devolución por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Devolución encontrada"),
        @ApiResponse(responseCode = "404", description = "Devolución no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DevolucionDTO.Response r = devolucionService.getDevolucionById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva devolución")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Devolución creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<DevolucionDTO.Response> crear(@Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una devolución existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Devolución actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Devolución no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.updateDevolucion(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una devolución por ID")
    @ApiResponse(responseCode = "200", description = "Devolución eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
