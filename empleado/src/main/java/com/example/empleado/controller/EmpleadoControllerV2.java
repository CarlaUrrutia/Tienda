package com.example.empleado.controller;

import com.example.empleado.assembler.EmpleadoModelAssembler;
import com.example.empleado.dto.EmpleadoDTO;
import com.example.empleado.service.EmpleadoService;
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

@Tag(name = "Empleados V2", description = "Gestión de empleados con HATEOAS")
@RestController
@RequestMapping("/api/v2/empleados")
@RequiredArgsConstructor
public class EmpleadoControllerV2 {

    private final EmpleadoService empleadoService;
    private final EmpleadoModelAssembler assembler;

    // GET /api/v2/empleados
    @Operation(summary = "Listar todos los empleados", description = "Retorna todos los empleados con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Empleados obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_empleado": 1,
                            "nombre": "Ana María",
                            "apellido": "López Soto",
                            "sueldo": 500000,
                            "tienda": { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                            "rol":    { "id_rol": 2, "nombre_rol": "Vendedor" },
                            "_links": {
                              "self":      { "href": "/api/v2/empleados/1" },
                              "empleados": { "href": "/api/v2/empleados" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/empleados" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<EmpleadoDTO.Response>> listarTodos() {
        List<EntityModel<EmpleadoDTO.Response>> empleados = empleadoService.getAllEmpleados().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(empleados,
                linkTo(methodOn(EmpleadoControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/empleados/{id}
    @Operation(summary = "Obtener empleado por ID", description = "Retorna un empleado específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Empleado encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Empleado encontrado",
                    value = """
                    {
                      "id_empleado": 1,
                      "nombre": "Ana María",
                      "apellido": "López Soto",
                      "sueldo": 500000,
                      "tienda": { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "rol":    { "id_rol": 2, "nombre_rol": "Vendedor" },
                      "_links": {
                        "self":      { "href": "/api/v2/empleados/1" },
                        "empleados": { "href": "/api/v2/empleados" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Empleado no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Empleado con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EmpleadoDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(empleadoService.getEmpleadoById(id)));
    }

    // POST /api/v2/empleados
    @Operation(summary = "Crear un nuevo empleado", description = "Registra un empleado y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Empleado creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Empleado registrado correctamente",
                    value = """
                    {
                      "id_empleado": 5,
                      "nombre": "Carlos Alberto",
                      "apellido": "Soto Muñoz",
                      "sueldo": 600000,
                      "tienda": { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "rol":    { "id_rol": 1, "nombre_rol": "Gerente" },
                      "_links": {
                        "self":      { "href": "/api/v2/empleados/5" },
                        "empleados": { "href": "/api/v2/empleados" }
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
                        "nombre":    "El nombre es obligatorio",
                        "apellido":  "El apellido es obligatorio",
                        "id_tienda": "El id de la tienda es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EmpleadoDTO.Response>> crear(@Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response nuevo = empleadoService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(EmpleadoControllerV2.class).buscarPorId(nuevo.getId_empleado())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/empleados/{id}
    @Operation(summary = "Actualizar un empleado existente", description = "Modifica los datos de un empleado por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Empleado actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Empleado modificado correctamente",
                    value = """
                    {
                      "id_empleado": 1,
                      "nombre": "Ana María",
                      "apellido": "López Soto",
                      "sueldo": 650000,
                      "tienda": { "id_tienda": 1, "nombre_tienda": "Tienda Central", "ubicacion": "Santiago", "horario_apertura": "2025-01-01", "politicas": "No devoluciones" },
                      "rol":    { "id_rol": 2, "nombre_rol": "Vendedor" },
                      "_links": {
                        "self":      { "href": "/api/v2/empleados/1" },
                        "empleados": { "href": "/api/v2/empleados" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Empleado no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Empleado con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EmpleadoDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EmpleadoDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(empleadoService.updateEmpleado(id, request)));
    }

    // DELETE /api/v2/empleados/{id}
    @Operation(summary = "Eliminar un empleado por ID", description = "Elimina permanentemente un empleado. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Empleado eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Empleado no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Empleado con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}