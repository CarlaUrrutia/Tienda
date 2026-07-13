package com.example.boleta.controller;

import com.example.boleta.dto.BoletaDTO;
import com.example.boleta.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Boletas", description = "Gestión de boletas")
@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @Operation(summary = "Listar todas las boletas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<BoletaDTO.Response> listar() {
        return boletaService.getAllBoletas();
    }

    @Operation(summary = "Obtener boleta por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Boleta encontrada"),
        @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BoletaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        BoletaDTO.Response boleta = boletaService.getBoletaById(id);
        return boleta != null ? ResponseEntity.ok(boleta) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva boleta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Boleta creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<BoletaDTO.Response> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Eliminar una boleta por ID")
    @ApiResponse(responseCode = "200", description = "Boleta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boletaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
