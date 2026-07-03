package com.example.factura.controller;

import com.example.factura.assembler.FacturaModelAssembler;
import com.example.factura.dto.FacturaDTO;
import com.example.factura.service.FacturaService;
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

@Tag(name = "Facturas V2", description = "Gestión de facturas con HATEOAS")
@RestController
@RequestMapping("/api/v2/facturas")
@RequiredArgsConstructor
public class FacturaControllerV2 {

    private final FacturaService facturaService;
    private final FacturaModelAssembler assembler;

    // GET /api/v2/facturas
    @Operation(summary = "Listar todas las facturas", description = "Retorna todas las facturas con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Facturas obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_factura": 1,
                            "fecha": "2025-06-01",
                            "total": 29900,
                            "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "venta":   { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                            "_links": {
                              "self":     { "href": "/api/v2/facturas/1" },
                              "facturas": { "href": "/api/v2/facturas" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/facturas" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<FacturaDTO.Response>> listarTodos() {
        List<EntityModel<FacturaDTO.Response>> facturas = facturaService.getAllFacturas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(facturas,
                linkTo(methodOn(FacturaControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/facturas/{id}
    @Operation(summary = "Obtener factura por ID", description = "Retorna una factura específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Factura encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Factura encontrada",
                    value = """
                    {
                      "id_factura": 1,
                      "fecha": "2025-06-01",
                      "total": 29900,
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "venta":   { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                      "_links": {
                        "self":     { "href": "/api/v2/facturas/1" },
                        "facturas": { "href": "/api/v2/facturas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Factura no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Factura con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<FacturaDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(facturaService.getFacturaById(id)));
    }

    // POST /api/v2/facturas
    @Operation(summary = "Crear una nueva factura", description = "Registra una factura y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Factura creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Factura registrada correctamente",
                    value = """
                    {
                      "id_factura": 5,
                      "fecha": "2025-06-22",
                      "total": 59800,
                      "cliente": { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "venta":   { "id_venta": 3, "fecha_venta": "2025-06-22", "id_cliente": 2, "id_empleado": 1 },
                      "_links": {
                        "self":     { "href": "/api/v2/facturas/5" },
                        "facturas": { "href": "/api/v2/facturas" }
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
                        "fecha":      "La fecha es obligatoria",
                        "id_venta":   "El id_venta es obligatorio",
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
    public ResponseEntity<EntityModel<FacturaDTO.Response>> crear(@Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response nueva = facturaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(FacturaControllerV2.class).buscarPorId(nueva.getId_factura())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/facturas/{id}
    @Operation(summary = "Actualizar una factura existente", description = "Modifica los datos de una factura por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Factura actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Factura modificada correctamente",
                    value = """
                    {
                      "id_factura": 1,
                      "fecha": "2025-06-01",
                      "total": 45000,
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "venta":   { "id_venta": 1, "fecha_venta": "2025-06-01", "id_cliente": 1, "id_empleado": 2 },
                      "_links": {
                        "self":     { "href": "/api/v2/facturas/1" },
                        "facturas": { "href": "/api/v2/facturas" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Factura no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Factura con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<FacturaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody FacturaDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(facturaService.updateFactura(id, request)));
    }

    // DELETE /api/v2/facturas/{id}
    @Operation(summary = "Eliminar una factura por ID", description = "Elimina permanentemente una factura. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Factura eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Factura no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Factura con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}