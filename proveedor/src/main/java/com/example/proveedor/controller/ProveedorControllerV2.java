package com.example.proveedor.controller;

import com.example.proveedor.assembler.ProveedorModelAssembler;
import com.example.proveedor.dto.ProveedorDTO;
import com.example.proveedor.service.ProveedorService;
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

@Tag(name = "Proveedores V2", description = "Gestión de proveedores con HATEOAS")
@RestController
@RequestMapping("/api/v2/proveedores")
@RequiredArgsConstructor
public class ProveedorControllerV2 {

    private final ProveedorService proveedorService;
    private final ProveedorModelAssembler assembler;

    // GET /api/v2/proveedores
    @Operation(summary = "Listar todos los proveedores", description = "Retorna todos los proveedores con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Proveedores obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_proveedor": 1,
                            "nombre": "Textiles Norte",
                            "contacto": "contacto@textilesnorte.cl",
                            "_links": {
                              "self":        { "href": "/api/v2/proveedores/1" },
                              "proveedores": { "href": "/api/v2/proveedores" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/proveedores" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<ProveedorDTO.Response>> listarTodos() {
        List<EntityModel<ProveedorDTO.Response>> proveedores = proveedorService.getAllProveedores().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/proveedores/{id}
    @Operation(summary = "Obtener proveedor por ID", description = "Retorna un proveedor específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Proveedor encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Proveedor encontrado",
                    value = """
                    {
                      "id_proveedor": 1,
                      "nombre": "Textiles Norte",
                      "contacto": "contacto@textilesnorte.cl",
                      "_links": {
                        "self":        { "href": "/api/v2/proveedores/1" },
                        "proveedores": { "href": "/api/v2/proveedores" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Proveedor no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Proveedor con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ProveedorDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(proveedorService.getProveedorById(id)));
    }

    // POST /api/v2/proveedores
    @Operation(summary = "Crear un nuevo proveedor", description = "Registra un proveedor y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Proveedor creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Proveedor registrado correctamente",
                    value = """
                    {
                      "id_proveedor": 5,
                      "nombre": "Confecciones Sur",
                      "contacto": "ventas@confeccionessur.cl",
                      "_links": {
                        "self":        { "href": "/api/v2/proveedores/5" },
                        "proveedores": { "href": "/api/v2/proveedores" }
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
                        "nombre":   "El nombre del proveedor es obligatorio",
                        "contacto": "El contacto del proveedor es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ProveedorDTO.Response>> crear(@Valid @RequestBody ProveedorDTO.Request request) {
        ProveedorDTO.Response nuevo = proveedorService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(ProveedorControllerV2.class).buscarPorId(nuevo.getId_proveedor())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/proveedores/{id}
    @Operation(summary = "Actualizar un proveedor existente", description = "Modifica los datos de un proveedor por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Proveedor actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Proveedor modificado correctamente",
                    value = """
                    {
                      "id_proveedor": 1,
                      "nombre": "Textiles Norte S.A.",
                      "contacto": "ventas@textilesnorte.cl",
                      "_links": {
                        "self":        { "href": "/api/v2/proveedores/1" },
                        "proveedores": { "href": "/api/v2/proveedores" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Proveedor no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Proveedor con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ProveedorDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProveedorDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(proveedorService.updateProveedor(id, request)));
    }

    // DELETE /api/v2/proveedores/{id}
    @Operation(summary = "Eliminar un proveedor por ID", description = "Elimina permanentemente un proveedor. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Proveedor eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Proveedor no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Proveedor con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}