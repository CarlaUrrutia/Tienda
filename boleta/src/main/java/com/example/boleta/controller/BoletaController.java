package com.example.boleta.controller;

import com.example.boleta.dto.ApiResponse;
import com.example.boleta.dto.BoletaDTO;
import com.example.boleta.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Boletas", description = "Gestión de boletas")
@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    // GET /api/boletas
    @Operation(summary = "Listar todas las boletas", description = "Retorna la lista completa de boletas.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Boletas obtenidas",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Boletas obtenidas exitosamente",
                      "data": [
                        {
                          "id_boleta": 1,
                          "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                          "venta": { "id_venta": 1, "fecha": "2025-06-01", "total": 15000 }
                        }
                      ],
                      "timestamp": "2025-06-22T10:00:00",
                      "links": { "self": "/api/boletas" }
                    }
                    """
                )
            )
        )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<BoletaDTO.Response>>> listar() {
        return ResponseEntity.ok(
            ApiResponse.ok(
                "Boletas obtenidas exitosamente",
                boletaService.getAllBoletas(),
                Map.of("self", "/api/boletas")
            )
        );
    }

    // GET /api/boletas/{id}
    @Operation(summary = "Obtener boleta por ID", description = "Retorna una boleta específica con cliente y venta asociados.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Boleta encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Boleta encontrada",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Boleta encontrada exitosamente",
                      "data": {
                        "id_boleta": 1,
                        "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                        "venta": { "id_venta": 1, "fecha": "2025-06-01", "total": 15000 }
                      },
                      "timestamp": "2025-06-22T10:00:00",
                      "links": {
                        "self": "/api/boletas/1",
                        "all": "/api/boletas",
                        "delete": "/api/boletas/1"
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Boleta no encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Boleta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoletaDTO.Response>> obtenerPorId(@PathVariable Integer id) {
        try {
            BoletaDTO.Response boleta = boletaService.getBoletaById(id);
            return ResponseEntity.ok(
                ApiResponse.ok(
                    "Boleta encontrada exitosamente",
                    boleta,
                    Map.of("self", "/api/boletas/" + id, "all", "/api/boletas", "delete", "/api/boletas/" + id)
                )
            );
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.notFound(ex.getMessage())
            );
        }
    }

    // POST /api/boletas
    @Operation(summary = "Crear una nueva boleta", description = "Registra una boleta asociando un cliente y una venta.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Boleta creada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Boleta registrada",
                    value = """
                    {
                      "status": 201,
                      "success": true,
                      "message": "Boleta creada exitosamente",
                      "data": {
                        "id_boleta": 5,
                        "cliente": { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                        "venta": { "id_venta": 3, "fecha": "2025-06-22", "total": 29900 }
                      },
                      "timestamp": "2025-06-22T10:00:00",
                      "links": {
                        "self": "/api/boletas/5",
                        "all": "/api/boletas",
                        "delete": "/api/boletas/5"
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Error validación",
                    summary = "Campos obligatorios faltantes",
                    value = """
                    {
                      "status": 400,
                      "success": false,
                      "message": "Error de validación en los datos enviados",
                      "errors": {
                        "id_cliente": "El id_cliente es obligatorio",
                        "id_venta": "El id_venta es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<BoletaDTO.Response>> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.created(
                "Boleta creada exitosamente",
                nueva,
                Map.of("self", "/api/boletas/" + nueva.getId_boleta(), "all", "/api/boletas", "delete", "/api/boletas/" + nueva.getId_boleta())
            )
        );
    }

    // DELETE /api/boletas/{id}
    @Operation(summary = "Eliminar una boleta por ID", description = "Elimina permanentemente una boleta del sistema.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Boleta eliminada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Eliminada",
                    summary = "Boleta borrada correctamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Boleta con id 1 eliminada exitosamente",
                      "timestamp": "2025-06-22T10:00:00",
                      "links": { "all": "/api/boletas" }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Boleta no encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Boleta con id 99 no encontrada para eliminar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Integer id) {
        try {
            boletaService.getBoletaById(id);
            boletaService.delete(id);
            return ResponseEntity.ok(
                ApiResponse.deleted(
                    "Boleta con id " + id + " eliminada exitosamente",
                    Map.of("all", "/api/boletas")
                )
            );
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.notFound("Boleta con id " + id + " no encontrada para eliminar")
            );
        }
    }
}