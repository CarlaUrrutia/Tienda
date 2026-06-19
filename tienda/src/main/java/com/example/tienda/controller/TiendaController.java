package com.example.tienda.controller;

import com.example.tienda.model.Tienda;
import com.example.tienda.service.TiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tiendas", description = "Gestión de tiendas")
@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Operation(summary = "Listar todas las tiendas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<Tienda> listar() {
        return tiendaService.getAllTiendas();
    }

    @Operation(summary = "Obtener tienda por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tienda encontrada"),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerPorId(@PathVariable Integer id) {
        Tienda tienda = tiendaService.getTiendaById(id);
        return tienda != null ? ResponseEntity.ok(tienda) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva tienda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tienda creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Tienda> crear(@RequestBody Tienda tienda) {
        Tienda nueva = tiendaService.save(tienda);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una tienda existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tienda actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Tienda> actualizar(@PathVariable Integer id, @RequestBody Tienda tienda) {
        Tienda actualizada = tiendaService.updateTienda(id, tienda);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una tienda por ID")
    @ApiResponse(responseCode = "200", description = "Tienda eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
