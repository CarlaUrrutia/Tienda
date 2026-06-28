package com.example.devolucion.controller;

import com.example.devolucion.assembler.DevolucionModelAssembler;
import com.example.devolucion.dto.DevolucionDTO;
import com.example.devolucion.service.DevolucionService;
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

@Tag(name = "Devoluciones V2", description = "Gestión de devoluciones con HATEOAS")
@RestController
@RequestMapping("/api/v2/devoluciones")
@RequiredArgsConstructor
public class DevolucionControllerV2 {

    private final DevolucionService devolucionService;
    private final DevolucionModelAssembler assembler;

    // GET /api/v2/devoluciones
    @Operation(summary = "Listar todas las devoluciones", description = "Retorna todas las devoluciones con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Devoluciones obtenidas exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_devolucion": 1,
                            "fecha_devolucion": "2025-06-01",
                            "motivo": "Producto defectuoso",
                            "monto_reembolso": 15000,
                            "cantidad_devuelta": 1,
                            "empleado":  { "id_empleado": 1, "nombre": "Ana", "apellido": "López", "sueldo": 500000, "id_tienda": 1 },
                            "cliente":   { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "tarjeta":   { "id_tarjeta": 1, "tipo": "D", "id_cliente": 1 },
                            "venta":     { "id_venta": 1, "fecha_venta": "2025-05-20", "id_cliente": 1, "id_empleado": 1 },
                            "producto":  { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                            "_links": {
                              "self":         { "href": "/api/v2/devoluciones/1" },
                              "devoluciones": { "href": "/api/v2/devoluciones" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/devoluciones" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<DevolucionDTO.Response>> listarTodos() {
        List<EntityModel<DevolucionDTO.Response>> devoluciones = devolucionService.getAllDevoluciones().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(devoluciones,
                linkTo(methodOn(DevolucionControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/devoluciones/{id}
    @Operation(summary = "Obtener devolución por ID", description = "Retorna una devolución específica con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Devolución encontrada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Devolución encontrada",
                    value = """
                    {
                      "id_devolucion": 1,
                      "fecha_devolucion": "2025-06-01",
                      "motivo": "Producto defectuoso",
                      "monto_reembolso": 15000,
                      "cantidad_devuelta": 1,
                      "empleado":  { "id_empleado": 1, "nombre": "Ana", "apellido": "López", "sueldo": 500000, "id_tienda": 1 },
                      "cliente":   { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "tarjeta":   { "id_tarjeta": 1, "tipo": "D", "id_cliente": 1 },
                      "venta":     { "id_venta": 1, "fecha_venta": "2025-05-20", "id_cliente": 1, "id_empleado": 1 },
                      "producto":  { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":         { "href": "/api/v2/devoluciones/1" },
                        "devoluciones": { "href": "/api/v2/devoluciones" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Devolución no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Devolución con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DevolucionDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(devolucionService.getDevolucionById(id)));
    }

    // POST /api/v2/devoluciones
    @Operation(summary = "Crear una nueva devolución", description = "Registra una devolución y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Devolución creada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creada",
                    summary = "Devolución registrada correctamente",
                    value = """
                    {
                      "id_devolucion": 5,
                      "fecha_devolucion": "2025-06-22",
                      "motivo": "Talla incorrecta",
                      "monto_reembolso": 29900,
                      "cantidad_devuelta": 1,
                      "empleado":  { "id_empleado": 2, "nombre": "Carlos", "apellido": "Soto", "sueldo": 600000, "id_tienda": 1 },
                      "cliente":   { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "tarjeta":   { "id_tarjeta": 2, "tipo": "C", "id_cliente": 2 },
                      "venta":     { "id_venta": 3, "fecha_venta": "2025-06-10", "id_cliente": 2, "id_empleado": 2 },
                      "producto":  { "id_producto": 4, "nombre": "Pantalon", "precio_venta": 29900, "id_proveedor": 2 },
                      "_links": {
                        "self":         { "href": "/api/v2/devoluciones/5" },
                        "devoluciones": { "href": "/api/v2/devoluciones" }
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
                        "motivo":           "El motivo es obligatorio",
                        "cantidad_devuelta": "La cantidad devuelta debe ser al menos 1"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DevolucionDTO.Response>> crear(@Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response nueva = devolucionService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(DevolucionControllerV2.class).buscarPorId(nueva.getId_devolucion())).toUri())
                .body(assembler.toModel(nueva));
    }

    // PUT /api/v2/devoluciones/{id}
    @Operation(summary = "Actualizar una devolución existente", description = "Modifica motivo, monto y cantidad de una devolución por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Devolución actualizada exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizada",
                    summary = "Devolución modificada correctamente",
                    value = """
                    {
                      "id_devolucion": 1,
                      "fecha_devolucion": "2025-06-01",
                      "motivo": "Producto en mal estado",
                      "monto_reembolso": 18000,
                      "cantidad_devuelta": 2,
                      "empleado":  { "id_empleado": 1, "nombre": "Ana", "apellido": "López", "sueldo": 500000, "id_tienda": 1 },
                      "cliente":   { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "tarjeta":   { "id_tarjeta": 1, "tipo": "D", "id_cliente": 1 },
                      "venta":     { "id_venta": 1, "fecha_venta": "2025-05-20", "id_cliente": 1, "id_empleado": 1 },
                      "producto":  { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":         { "href": "/api/v2/devoluciones/1" },
                        "devoluciones": { "href": "/api/v2/devoluciones" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Devolución no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Devolución con id 99 no encontrada para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DevolucionDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DevolucionDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(devolucionService.updateDevolucion(id, request)));
    }

    // DELETE /api/v2/devoluciones/{id}
    @Operation(summary = "Eliminar una devolución por ID", description = "Elimina permanentemente una devolución. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Devolución eliminada exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Devolución no encontrada",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrada",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Devolución con id 99 no encontrada",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}