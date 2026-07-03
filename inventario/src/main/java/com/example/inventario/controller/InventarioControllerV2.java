package com.example.inventario.controller;

import com.example.inventario.assembler.InventarioModelAssembler;
import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.service.InventarioService;
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

@Tag(name = "Inventarios V2", description = "Gestión de inventarios con HATEOAS")
@RestController
@RequestMapping("/api/v2/inventarios")
@RequiredArgsConstructor
public class InventarioControllerV2 {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;

    // GET /api/v2/inventarios
    @Operation(summary = "Listar todos los inventarios", description = "Retorna todos los inventarios con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Inventarios obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_inventario": 1,
                            "cantidad": 50,
                            "tienda":   { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                            "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                            "_links": {
                              "self":        { "href": "/api/v2/inventarios/1" },
                              "inventarios": { "href": "/api/v2/inventarios" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/inventarios" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<InventarioDTO.Response>> listarTodos() {
        List<EntityModel<InventarioDTO.Response>> inventarios = inventarioService.getAllInventarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/inventarios/{id}
    @Operation(summary = "Obtener inventario por ID", description = "Retorna un inventario específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Inventario encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Inventario encontrado",
                    value = """
                    {
                      "id_inventario": 1,
                      "cantidad": 50,
                      "tienda":   { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":        { "href": "/api/v2/inventarios/1" },
                        "inventarios": { "href": "/api/v2/inventarios" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Inventario con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<InventarioDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(inventarioService.getInventarioById(id)));
    }

    // POST /api/v2/inventarios
    @Operation(summary = "Crear un nuevo inventario", description = "Registra un inventario y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Inventario creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Inventario registrado correctamente",
                    value = """
                    {
                      "id_inventario": 5,
                      "cantidad": 100,
                      "tienda":   { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "producto": { "id_producto": 3, "nombre": "Pantalon", "precio_venta": 29900, "id_proveedor": 2 },
                      "_links": {
                        "self":        { "href": "/api/v2/inventarios/5" },
                        "inventarios": { "href": "/api/v2/inventarios" }
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
                        "id_tienda":   "El id_tienda es obligatorio",
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
    public ResponseEntity<EntityModel<InventarioDTO.Response>> crear(@Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response nuevo = inventarioService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).buscarPorId(nuevo.getId_inventario())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/inventarios/{id}
    @Operation(summary = "Actualizar un inventario existente", description = "Modifica la cantidad, tienda y producto de un inventario por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Inventario actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Inventario modificado correctamente",
                    value = """
                    {
                      "id_inventario": 1,
                      "cantidad": 75,
                      "tienda":   { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "producto": { "id_producto": 1, "nombre": "Camiseta", "precio_venta": 15000, "id_proveedor": 1 },
                      "_links": {
                        "self":        { "href": "/api/v2/inventarios/1" },
                        "inventarios": { "href": "/api/v2/inventarios" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Inventario con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<InventarioDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody InventarioDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(inventarioService.updateInventario(id, request)));
    }

    // DELETE /api/v2/inventarios/{id}
    @Operation(summary = "Eliminar un inventario por ID", description = "Elimina permanentemente un inventario. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Inventario eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Inventario con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}