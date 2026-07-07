package com.example.boleta.controller;

import com.example.boleta.assembler.BoletaModelAssembler;
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
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Boletas V2", description = "Gestión de boletas con HATEOAS")
@RestController
@RequestMapping("/api/v2/boletas")
@RequiredArgsConstructor
public class BoletaControllerV2 {

    private final BoletaService boletaService;
    private final BoletaModelAssembler assembler;

    // GET /api/v2/boletas
    @Operation(summary = "Listar todas las boletas", description = "Retorna todas las boletas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Boletas obtenidas exitosamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Boletas obtenidas exitosamente",
                      "data": {
                        "_embedded": {
                          "responseList": [
                            {
                              "id_boleta": 1,
                              "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                              "venta": { "id_venta": 1, "fecha": "2025-06-01", "total": 15000 },
                              "_links": {
                                "self":    { "href": "/api/v2/boletas/1" },
                                "boletas": { "href": "/api/v2/boletas" }
                              }
                            }
                          ]
                        },
                        "_links": { "self": { "href": "/api/v2/boletas" } }
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<CollectionModel<EntityModel<BoletaDTO.Response>>>> listarTodos() {
        List<EntityModel<BoletaDTO.Response>> boletas = boletaService.getAllBoletas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<BoletaDTO.Response>> collection = CollectionModel.of(boletas,
                linkTo(methodOn(BoletaControllerV2.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(
            ApiResponse.ok("Boletas obtenidas exitosamente", collection, null)
        );
    }

    // GET /api/v2/boletas/{id}
    @Operation(summary = "Obtener boleta por ID", description = "Retorna una boleta específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Boleta encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
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
                        "venta": { "id_venta": 1, "fecha": "2025-06-01", "total": 15000 },
                        "_links": {
                          "self":    { "href": "/api/v2/boletas/1" },
                          "boletas": { "href": "/api/v2/boletas" },
                          "delete":  { "href": "/api/v2/boletas/1" }
                        }
                      },
                      "timestamp": "2025-06-22T10:00:00"
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
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<EntityModel<BoletaDTO.Response>>> buscarPorId(@PathVariable Integer id) {
        try {
            BoletaDTO.Response boleta = boletaService.getBoletaById(id);
            return ResponseEntity.ok(
                ApiResponse.ok("Boleta encontrada exitosamente", assembler.toModel(boleta), null)
            );
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.notFound(ex.getMessage())
            );
        }
    }

    // POST /api/v2/boletas
    @Operation(summary = "Crear una nueva boleta", description = "Registra una boleta asociando un cliente y una venta.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Boleta creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Boleta registrada correctamente",
                    value = """
                    {
                      "status": 201,
                      "success": true,
                      "message": "Boleta creada exitosamente",
                      "data": {
                        "id_boleta": 5,
                        "cliente": { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                        "venta": { "id_venta": 3, "fecha": "2025-06-22", "total": 29900 },
                        "_links": {
                          "self":    { "href": "/api/v2/boletas/5" },
                          "boletas": { "href": "/api/v2/boletas" },
                          "delete":  { "href": "/api/v2/boletas/5" }
                        }
                      },
                      "timestamp": "2025-06-22T10:00:00"
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
                        "id_venta":   "El id_venta es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<EntityModel<BoletaDTO.Response>>> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.created("Boleta creada exitosamente", assembler.toModel(nueva), null)
        );
    }

    // DELETE /api/v2/boletas/{id}
    @Operation(summary = "Eliminar una boleta por ID", description = "Elimina permanentemente una boleta.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Boleta eliminada exitosamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Eliminada",
                    summary = "Boleta borrada correctamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Boleta con id 1 eliminada exitosamente",
                      "timestamp": "2025-06-22T10:00:00"
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
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Integer id) {
        try {
            boletaService.getBoletaById(id);
            boletaService.delete(id);
            return ResponseEntity.ok(
                ApiResponse.deleted("Boleta con id " + id + " eliminada exitosamente", null)
            );
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.notFound("Boleta con id " + id + " no encontrada para eliminar")
            );
        }
    }
}