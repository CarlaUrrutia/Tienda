package com.example.envio.controller;

import com.example.envio.DTO.EnvioDTO;
import com.example.envio.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Envíos", description = "Gestión de envíos")
@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Operation(summary = "Listar todos los envíos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<EnvioDTO.Response> listar() {
        return envioService.getAllEnvios();
    }

    @Operation(summary = "Obtener envío por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Envío encontrado"),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EnvioDTO.Response r = envioService.getEnvioById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo envío")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Envío creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<EnvioDTO.Response> crear(@Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un envío existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Envío actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.updateEnvio(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un envío por ID")
    @ApiResponse(responseCode = "200", description = "Envío eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
