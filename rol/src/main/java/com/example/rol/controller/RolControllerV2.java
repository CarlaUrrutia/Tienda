package com.example.rol.controller;

import com.example.rol.assembler.RolModelAssembler;
import com.example.rol.dto.RolDTO;
import com.example.rol.service.RolService;
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

@Tag(name = "Roles V2", description = "Gestión de roles con HATEOAS")
@RestController
@RequestMapping("/api/v2/roles")
@RequiredArgsConstructor
public class RolControllerV2 {

    private final RolService rolService;
    private final RolModelAssembler assembler;

    // GET /api/v2/roles
    @Operation(summary = "Listar todos los roles", description = "Retorna todos los roles con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Roles obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_rol": 1,
                            "nombre_rol": "Gerente de Tienda",
                            "_links": {
                              "self":  { "href": "/api/v2/roles/1" },
                              "roles": { "href": "/api/v2/roles" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/roles" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<RolDTO.Response>> listarTodos() {
        List<EntityModel<RolDTO.Response>> roles = rolService.getAllRoles().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(roles,
                linkTo(methodOn(RolControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/roles/{id}
    @Operation(summary = "Obtener rol por ID", description = "Retorna un rol específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Rol encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Rol encontrado",
                    value = """
                    {
                      "id_rol": 1,
                      "nombre_rol": "Gerente de Tienda",
                      "_links": {
                        "self":  { "href": "/api/v2/roles/1" },
                        "roles": { "href": "/api/v2/roles" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Rol con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<RolDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(rolService.getRolById(id)));
    }

    // POST /api/v2/roles
    @Operation(summary = "Crear un nuevo rol", description = "Registra un rol y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Rol creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Rol registrado correctamente",
                    value = """
                    {
                      "id_rol": 5,
                      "nombre_rol": "Jefe de Bodega",
                      "_links": {
                        "self":  { "href": "/api/v2/roles/5" },
                        "roles": { "href": "/api/v2/roles" }
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
                        "nombre_rol": "El nombre_rol es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<RolDTO.Response>> crear(@Valid @RequestBody RolDTO.Request request) {
        RolDTO.Response nuevo = rolService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(RolControllerV2.class).buscarPorId(nuevo.getId_rol())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/roles/{id}
    @Operation(summary = "Actualizar un rol existente", description = "Modifica el nombre de un rol por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Rol actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Rol modificado correctamente",
                    value = """
                    {
                      "id_rol": 1,
                      "nombre_rol": "Director de Tienda",
                      "_links": {
                        "self":  { "href": "/api/v2/roles/1" },
                        "roles": { "href": "/api/v2/roles" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Rol con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<RolDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RolDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(rolService.updateRol(id, request)));
    }

    // DELETE /api/v2/roles/{id}
    @Operation(summary = "Eliminar un rol por ID", description = "Elimina permanentemente un rol. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Rol eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Rol con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}