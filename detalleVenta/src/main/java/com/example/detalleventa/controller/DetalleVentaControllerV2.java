package com.example.detalleventa.controller;

import com.example.detalleventa.assembler.DetalleVentaModelAssembler;
import com.example.detalleventa.dto.DetalleVentaDTO;
import com.example.detalleventa.service.DetalleVentaService;
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

@Tag(name = "Detalles de Venta V2", description = "Gestión de detalles de venta con HATEOAS")
@RestController
@RequestMapping("/api/v2/detalles")
@RequiredArgsConstructor
public class DetalleVentaControllerV2 {

    private final DetalleVentaService detalleVentaService;
    private final DetalleVentaModelAssembler assembler;

    // GET /api/v2/detalles
    @Operation(summary = "Listar todos los detalles de venta", description = "Retorna todos los detalles con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Detalles obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_detalle": 1,
                            "cantidad": 3,
                            "precio_unitario_venta": 5000,
                            "venta": { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                            "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 5000, "id_proveedor": 1 },
                            "_links": {
                              "self":     { "href": "/api/v2/detalles/1" },
                              "detalles": { "href": "/api/v2/detalles" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/detalles" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<DetalleVentaDTO.Response>> listarTodos() {
        List<EntityModel<DetalleVentaDTO.Response>> detalles = detalleVentaService.getAllDetalles().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(detalles,
                linkTo(methodOn(DetalleVentaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/detalles/{id}
    @Operation(summary = "Obtener detalle por ID", description = "Retorna un detalle de venta específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Detalle encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Detalle encontrado",
                    value = """
                    {
                      "id_detalle": 1,
                      "cantidad": 3,
                      "precio_unitario_venta": 5000,
                      "venta": { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 5000, "id_proveedor": 1 },
                      "_links": {
                        "self":     { "href": "/api/v2/detalles/1" },
                        "detalles": { "href": "/api/v2/detalles" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Detalle no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Detalle de venta con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DetalleVentaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(detalleVentaService.getDetalleById(id)));
    }

    // GET /api/v2/detalles/venta/{idVenta}
    @Operation(summary = "Obtener detalles por venta", description = "Retorna todos los detalles asociados a una venta específica.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Detalles de la venta obtenidos correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Detalles de venta encontrados",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_detalle": 1,
                            "cantidad": 3,
                            "precio_unitario_venta": 5000,
                            "venta": { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                            "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 5000, "id_proveedor": 1 },
                            "_links": {
                              "self":     { "href": "/api/v2/detalles/1" },
                              "detalles": { "href": "/api/v2/detalles" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/detalles/venta/1" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/venta/{idVenta}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<DetalleVentaDTO.Response>> buscarPorVenta(@PathVariable Integer idVenta) {
        List<EntityModel<DetalleVentaDTO.Response>> detalles = detalleVentaService.getDetallesByVenta(idVenta).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(detalles,
                linkTo(methodOn(DetalleVentaControllerV2.class).buscarPorVenta(idVenta)).withSelfRel());
    }

    // POST /api/v2/detalles
    @Operation(summary = "Crear un nuevo detalle de venta", description = "Registra un detalle de venta y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Detalle creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Detalle registrado correctamente",
                    value = """
                    {
                      "id_detalle": 5,
                      "cantidad": 2,
                      "precio_unitario_venta": 8900,
                      "venta": { "id_venta": 2, "fecha_venta": "2025-06-22", "id_cliente": 3, "id_empleado": 1 },
                      "producto": { "id_producto": 4, "nombre": "Pantalon", "precio_venta": 8900, "id_proveedor": 2 },
                      "_links": {
                        "self":     { "href": "/api/v2/detalles/5" },
                        "detalles": { "href": "/api/v2/detalles" }
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
                        "cantidad":    "La cantidad debe ser al menos 1",
                        "id_venta":    "El id_venta es obligatorio",
                        "id_producto": "El id_producto es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DetalleVentaDTO.Response>> crear(@Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response nuevo = detalleVentaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(DetalleVentaControllerV2.class).buscarPorId(nuevo.getId_detalle())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/detalles/{id}
    @Operation(summary = "Actualizar un detalle de venta", description = "Modifica los datos de un detalle por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Detalle actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Detalle modificado correctamente",
                    value = """
                    {
                      "id_detalle": 1,
                      "cantidad": 5,
                      "precio_unitario_venta": 5000,
                      "venta": { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 5000, "id_proveedor": 1 },
                      "_links": {
                        "self":     { "href": "/api/v2/detalles/1" },
                        "detalles": { "href": "/api/v2/detalles" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Detalle no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Detalle de venta con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DetalleVentaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVentaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(detalleVentaService.updateDetalle(id, request)));
    }

    // DELETE /api/v2/detalles/{id}
    @Operation(summary = "Eliminar un detalle de venta por ID", description = "Elimina permanentemente un detalle. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Detalle eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Detalle no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Detalle de venta con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}