package com.example.venta.controller;

import com.example.venta.assembler.VentaModelAssembler;
import com.example.venta.dto.VentaDTO;
import com.example.venta.service.VentaService;
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

@Tag(name = "Ventas V2", description = "Gestión de ventas con HATEOAS")
@RestController
@RequestMapping("/api/v2/ventas")
@RequiredArgsConstructor
public class VentaControllerV2 {

    private final VentaService ventaService;
    private final VentaModelAssembler assembler;

    // GET /api/v2/ventas
    @Operation(summary = "Listar todas las ventas", description = "Retorna todas las ventas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Ventas obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_venta": 1,
                            "fecha_venta": "2025-06-01",
                            "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "empleado": { "id_empleado": 2, "nombre": "Ana María", "apellido": "López Soto", "sueldo": 500000 },
                            "_links": {
                              "self":   { "href": "/api/v2/ventas/1" },
                              "ventas": { "href": "/api/v2/ventas" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/ventas" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<VentaDTO.Response>> listarTodos() {
        List<EntityModel<VentaDTO.Response>> ventas = ventaService.getAllVentas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ventas,
                linkTo(methodOn(VentaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/ventas/{id}
    @Operation(summary = "Obtener venta por ID", description = "Retorna una venta específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Venta encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Venta encontrada",
                    value = """
                    {
                      "id_venta": 1,
                      "fecha_venta": "2025-06-01",
                      "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "empleado": { "id_empleado": 2, "nombre": "Ana María", "apellido": "López Soto", "sueldo": 500000 },
                      "_links": {
                        "self":   { "href": "/api/v2/ventas/1" },
                        "ventas": { "href": "/api/v2/ventas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Venta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<VentaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(ventaService.getVentaById(id)));
    }

    // POST /api/v2/ventas
    @Operation(summary = "Crear una nueva venta", description = "Registra una venta y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Venta creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Venta registrada correctamente",
                    value = """
                    {
                      "id_venta": 5,
                      "fecha_venta": "2025-06-22",
                      "cliente":  { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "empleado": { "id_empleado": 1, "nombre": "Carlos Alberto", "apellido": "Soto Muñoz", "sueldo": 600000 },
                      "_links": {
                        "self":   { "href": "/api/v2/ventas/5" },
                        "ventas": { "href": "/api/v2/ventas" }
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
                        "fecha_venta":  "La fecha de venta es obligatoria",
                        "id_cliente":   "El id_cliente es obligatorio",
                        "id_empleado":  "El id_empleado es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<VentaDTO.Response>> crear(@Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response nueva = ventaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(VentaControllerV2.class).buscarPorId(nueva.getId_venta())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/ventas/{id}
    @Operation(summary = "Actualizar una venta existente", description = "Modifica los datos de una venta por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Venta actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Venta modificada correctamente",
                    value = """
                    {
                      "id_venta": 1,
                      "fecha_venta": "2025-06-01",
                      "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "empleado": { "id_empleado": 3, "nombre": "Pedro", "apellido": "Ramírez", "sueldo": 550000 },
                      "_links": {
                        "self":   { "href": "/api/v2/ventas/1" },
                        "ventas": { "href": "/api/v2/ventas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Venta con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<VentaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody VentaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(ventaService.updateVenta(id, request)));
    }

    // DELETE /api/v2/ventas/{id}
    @Operation(summary = "Eliminar una venta por ID", description = "Elimina permanentemente una venta. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Venta eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Venta con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}