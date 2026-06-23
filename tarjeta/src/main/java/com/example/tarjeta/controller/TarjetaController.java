package com.example.tarjeta.controller;

import com.example.tarjeta.dto.TarjetaDTO;
import com.example.tarjeta.service.TarjetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tarjetas", description = "Gestión de tarjetas de clientes")
@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Operation(summary = "Listar todas las tarjetas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<TarjetaDTO.Response> listar() {
        return tarjetaService.getAllTarjetas();
    }

    @Operation(summary = "Obtener tarjeta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarjeta encontrada"),
        @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        TarjetaDTO.Response r = tarjetaService.getTarjetaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva tarjeta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarjeta creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<TarjetaDTO.Response> crear(@Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar una tarjeta existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarjeta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.updateTarjeta(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una tarjeta por ID")
    @ApiResponse(responseCode = "200", description = "Tarjeta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
