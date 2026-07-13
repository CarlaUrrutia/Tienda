package com.example.inventario.controller;

import com.example.inventario.DTO.InventarioDTO;
import com.example.inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inventario", description = "Gestión de inventario")
@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Operation(summary = "Listar todos los inventarios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<InventarioDTO.Response> listar() {
        return inventarioService.getAllInventarios();
    }

    @Operation(summary = "Obtener inventario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        InventarioDTO.Response r = inventarioService.getInventarioById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo inventario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<InventarioDTO.Response> crear(@Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un inventario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response r = inventarioService.updateInventario(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un inventario por ID")
    @ApiResponse(responseCode = "200", description = "Inventario eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
