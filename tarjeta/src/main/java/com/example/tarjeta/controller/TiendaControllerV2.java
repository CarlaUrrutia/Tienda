package com.example.tienda.controller;

import com.example.tienda.assembler.TiendaModelAssembler;
import com.example.tienda.dto.TiendaDTO;
import com.example.tienda.service.TiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Tiendas V2", description = "Gestión de tiendas con HATEOAS")
@RestController
@RequestMapping("/api/v2/tiendas")
@RequiredArgsConstructor
public class TiendaControllerV2 {

    private final TiendaService tiendaService;
    private final TiendaModelAssembler assembler;

    // GET /api/v2/tiendas
    @Operation(summary = "Listar todas las tiendas", description = "Retorna todas las tiendas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Tiendas obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_tienda": 1,
                            "nombre_tienda": "Tienda Central Santiago",
                            "ubicacion": "Av. Providencia 1234 Santiago",
                            "horario_apertura": "2025-01-01",
                            "politicas": "No se aceptan devoluciones sin boleta",
                            "_links": {
                              "self":    { "href": "/api/v2/tiendas/1" },
                              "tiendas": { "href": "/api/v2/tiendas" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/tiendas" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<TiendaDTO.Response>> listarTodos() {
        List<EntityModel<TiendaDTO.Response>> tiendas = tiendaService.getAllTiendas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tiendas,
                linkTo(methodOn(TiendaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/tiendas/{id}
    @Operation(summary = "Obtener tienda por ID", description = "Retorna una tienda específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Tienda encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Tienda encontrada",
                    value = """
                    {
                      "id_tienda": 1,
                      "nombre_tienda": "Tienda Central Santiago",
                      "ubicacion": "Av. Providencia 1234 Santiago",
                      "horario_apertura": "2025-01-01",
                      "politicas": "No se aceptan devoluciones sin boleta",
                      "_links": {
                        "self":    { "href": "/api/v2/tiendas/1" },
                        "tiendas": { "href": "/api/v2/tiendas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tienda no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tienda con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TiendaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(tiendaService.getTiendaById(id)));
    }

    // POST /api/v2/tiendas
    @Operation(summary = "Crear una nueva tienda", description = "Registra una tienda y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Tienda creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Tienda registrada correctamente",
                    value = """
                    {
                      "id_tienda": 5,
                      "nombre_tienda": "Tienda Norte Concepcion",
                      "ubicacion": "Av. O'Higgins 567 Concepcion",
                      "horario_apertura": "2025-03-01",
                      "politicas": "Cambios dentro de 30 dias con boleta",
                      "_links": {
                        "self":    { "href": "/api/v2/tiendas/5" },
                        "tiendas": { "href": "/api/v2/tiendas" }
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
                examples = @ExampleObject(
                    name = "Error validación",
                    summary = "Campos obligatorios faltantes",
                    value = """
                    {
                      "status": 400,
                      "success": false,
                      "message": "Error de validación en los datos enviados",
                      "errors": {
                        "nombre_tienda": "El nombre de la tienda es obligatorio",
                        "ubicacion":     "La ubicacion de la tienda es obligatoria",
                        "politicas":     "Las politicas de la tienda son obligatorias"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TiendaDTO.Response>> crear(@Valid @RequestBody TiendaDTO.Request request) {
        TiendaDTO.Response nueva = tiendaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(TiendaControllerV2.class).buscarPorId(nueva.getId_tienda())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/tiendas/{id}
    @Operation(summary = "Actualizar una tienda existente", description = "Modifica los datos de una tienda por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Tienda actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Tienda modificada correctamente",
                    value = """
                    {
                      "id_tienda": 1,
                      "nombre_tienda": "Tienda Central Santiago Premium",
                      "ubicacion": "Av. Providencia 1234 Santiago",
                      "horario_apertura": "2025-01-01",
                      "politicas": "Devoluciones hasta 15 dias con boleta",
                      "_links": {
                        "self":    { "href": "/api/v2/tiendas/1" },
                        "tiendas": { "href": "/api/v2/tiendas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tienda no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tienda con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TiendaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TiendaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(tiendaService.updateTienda(id, request)));
    }

    // DELETE /api/v2/tiendas/{id}
    @Operation(summary = "Eliminar una tienda por ID", description = "Elimina permanentemente una tienda. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Tienda eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tienda no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tienda con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}