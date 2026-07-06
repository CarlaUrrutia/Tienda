package com.example.tarjeta.controller;

import com.example.tarjeta.assembler.TarjetaModelAssembler;
import com.example.tarjeta.dto.TarjetaDTO;
import com.example.tarjeta.service.TarjetaService;
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

@Tag(name = "Tarjetas V2", description = "Gestión de tarjetas con HATEOAS")
@RestController
@RequestMapping("/api/v2/tarjetas")
@RequiredArgsConstructor
public class TarjetaControllerV2 {

    private final TarjetaService tarjetaService;
    private final TarjetaModelAssembler assembler;

    // GET /api/v2/tarjetas
    @Operation(summary = "Listar todas las tarjetas", description = "Retorna todas las tarjetas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Tarjetas obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_tarjeta": 1,
                            "tipo": "D",
                            "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "_links": {
                              "self":     { "href": "/api/v2/tarjetas/1" },
                              "tarjetas": { "href": "/api/v2/tarjetas" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/tarjetas" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<TarjetaDTO.Response>> listarTodos() {
        List<EntityModel<TarjetaDTO.Response>> tarjetas = tarjetaService.getAllTarjetas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarjetas,
                linkTo(methodOn(TarjetaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/tarjetas/{id}
    @Operation(summary = "Obtener tarjeta por ID", description = "Retorna una tarjeta específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Tarjeta encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Tarjeta encontrada",
                    value = """
                    {
                      "id_tarjeta": 1,
                      "tipo": "D",
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "_links": {
                        "self":     { "href": "/api/v2/tarjetas/1" },
                        "tarjetas": { "href": "/api/v2/tarjetas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tarjeta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tarjeta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TarjetaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(tarjetaService.getTarjetaById(id)));
    }

    // POST /api/v2/tarjetas
    @Operation(summary = "Crear una nueva tarjeta", description = "Registra una tarjeta y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Tarjeta creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Tarjeta registrada correctamente",
                    value = """
                    {
                      "id_tarjeta": 5,
                      "tipo": "C",
                      "cliente": { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "_links": {
                        "self":     { "href": "/api/v2/tarjetas/5" },
                        "tarjetas": { "href": "/api/v2/tarjetas" }
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
                        "tipo":       "El tipo de tarjeta es obligatorio",
                        "id_cliente": "El id_cliente es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TarjetaDTO.Response>> crear(@Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response nueva = tarjetaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(TarjetaControllerV2.class).buscarPorId(nueva.getId_tarjeta())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/tarjetas/{id}
    @Operation(summary = "Actualizar una tarjeta existente", description = "Modifica el tipo y cliente de una tarjeta por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Tarjeta actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Tarjeta modificada correctamente",
                    value = """
                    {
                      "id_tarjeta": 1,
                      "tipo": "C",
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "_links": {
                        "self":     { "href": "/api/v2/tarjetas/1" },
                        "tarjetas": { "href": "/api/v2/tarjetas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tarjeta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tarjeta con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TarjetaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TarjetaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(tarjetaService.updateTarjeta(id, request)));
    }

    // DELETE /api/v2/tarjetas/{id}
    @Operation(summary = "Eliminar una tarjeta por ID", description = "Elimina permanentemente una tarjeta. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Tarjeta eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Tarjeta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Tarjeta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}