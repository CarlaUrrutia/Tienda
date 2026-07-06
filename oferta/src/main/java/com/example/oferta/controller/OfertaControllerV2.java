package com.example.oferta.controller;

import com.example.oferta.assembler.OfertaModelAssembler;
import com.example.oferta.dto.OfertaDTO;
import com.example.oferta.service.OfertaService;
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

@Tag(name = "Ofertas V2", description = "Gestión de ofertas con HATEOAS")
@RestController
@RequestMapping("/api/v2/ofertas")
@RequiredArgsConstructor
public class OfertaControllerV2 {

    private final OfertaService ofertaService;
    private final OfertaModelAssembler assembler;

    // GET /api/v2/ofertas
    @Operation(summary = "Listar todas las ofertas", description = "Retorna todas las ofertas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Ofertas obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_oferta": 1,
                            "descripcion": "Descuento de verano en camisetas",
                            "descuento": 20,
                            "fecha_inicio": "2025-12-01",
                            "fecha_fin": "2025-12-31",
                            "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                            "_links": {
                              "self":    { "href": "/api/v2/ofertas/1" },
                              "ofertas": { "href": "/api/v2/ofertas" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/ofertas" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<OfertaDTO.Response>> listarTodos() {
        List<EntityModel<OfertaDTO.Response>> ofertas = ofertaService.getAllOfertas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ofertas,
                linkTo(methodOn(OfertaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/ofertas/{id}
    @Operation(summary = "Obtener oferta por ID", description = "Retorna una oferta específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Oferta encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Oferta encontrada",
                    value = """
                    {
                      "id_oferta": 1,
                      "descripcion": "Descuento de verano en camisetas",
                      "descuento": 20,
                      "fecha_inicio": "2025-12-01",
                      "fecha_fin": "2025-12-31",
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":    { "href": "/api/v2/ofertas/1" },
                        "ofertas": { "href": "/api/v2/ofertas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Oferta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<OfertaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(ofertaService.getOfertaById(id)));
    }

    // POST /api/v2/ofertas
    @Operation(summary = "Crear una nueva oferta", description = "Registra una oferta y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Oferta creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Oferta registrada correctamente",
                    value = """
                    {
                      "id_oferta": 5,
                      "descripcion": "Promoción de invierno en abrigos",
                      "descuento": 30,
                      "fecha_inicio": "2025-07-01",
                      "fecha_fin": "2025-07-31",
                      "producto": { "id_producto": 3, "nombre": "Abrigo", "precio_venta": 59900, "id_proveedor": 2 },
                      "_links": {
                        "self":    { "href": "/api/v2/ofertas/5" },
                        "ofertas": { "href": "/api/v2/ofertas" }
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
                        "descripcion":  "La descripcion es obligatoria",
                        "fecha_inicio": "La fecha de inicio es obligatoria",
                        "fecha_fin":    "La fecha de fin es obligatoria",
                        "id_producto":  "El id_producto es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<OfertaDTO.Response>> crear(@Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response nueva = ofertaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(OfertaControllerV2.class).buscarPorId(nueva.getId_oferta())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/ofertas/{id}
    @Operation(summary = "Actualizar una oferta existente", description = "Modifica los datos de una oferta por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Oferta actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Oferta modificada correctamente",
                    value = """
                    {
                      "id_oferta": 1,
                      "descripcion": "Descuento de verano ampliado",
                      "descuento": 25,
                      "fecha_inicio": "2025-12-01",
                      "fecha_fin": "2026-01-15",
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":    { "href": "/api/v2/ofertas/1" },
                        "ofertas": { "href": "/api/v2/ofertas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Oferta con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<OfertaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OfertaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(ofertaService.updateOferta(id, request)));
    }

    // DELETE /api/v2/ofertas/{id}
    @Operation(summary = "Eliminar una oferta por ID", description = "Elimina permanentemente una oferta. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Oferta eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Oferta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}