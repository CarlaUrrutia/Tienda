package com.example.cupon.controller;

import com.example.cupon.assembler.CuponModelAssembler;
import com.example.cupon.dto.CuponDTO;
import com.example.cupon.service.CuponService;
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

@Tag(name = "Cupones V2", description = "Gestión de cupones con HATEOAS")
@RestController
@RequestMapping("/api/v2/cupones")
@RequiredArgsConstructor
public class CuponControllerV2 {

    private final CuponService cuponService;
    private final CuponModelAssembler assembler;

    // GET /api/v2/cupones
    @Operation(summary = "Listar todos los cupones", description = "Retorna todos los cupones con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Cupones obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_cupon": 1,
                            "codigo": "DESC10",
                            "descuento": 10,
                            "fecha_expiracion": "2025-12-31",
                            "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "_links": {
                              "self":    { "href": "/api/v2/cupones/1" },
                              "cupones": { "href": "/api/v2/cupones" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/cupones" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<CuponDTO.Response>> listarTodos() {
        List<EntityModel<CuponDTO.Response>> cupones = cuponService.getAllCupones().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cupones,
                linkTo(methodOn(CuponControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/cupones/{id}
    @Operation(summary = "Obtener cupón por ID", description = "Retorna un cupón específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cupón encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Cupón encontrado",
                    value = """
                    {
                      "id_cupon": 1,
                      "codigo": "DESC10",
                      "descuento": 10,
                      "fecha_expiracion": "2025-12-31",
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "_links": {
                        "self":    { "href": "/api/v2/cupones/1" },
                        "cupones": { "href": "/api/v2/cupones" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cupón no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cupón con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<CuponDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(cuponService.getCuponById(id)));
    }

    // POST /api/v2/cupones
    @Operation(summary = "Crear un nuevo cupón", description = "Registra un cupón y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Cupón creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Cupón registrado correctamente",
                    value = """
                    {
                      "id_cupon": 5,
                      "codigo": "PROMO20",
                      "descuento": 20,
                      "fecha_expiracion": "2025-12-31",
                      "cliente": { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "_links": {
                        "self":    { "href": "/api/v2/cupones/5" },
                        "cupones": { "href": "/api/v2/cupones" }
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
                        "codigo":     "El codigo es obligatorio",
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
    public ResponseEntity<EntityModel<CuponDTO.Response>> crear(@Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response nuevo = cuponService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(CuponControllerV2.class).buscarPorId(nuevo.getId_cupon())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/cupones/{id}
    @Operation(summary = "Actualizar un cupón existente", description = "Modifica los datos de un cupón por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cupón actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Cupón modificado correctamente",
                    value = """
                    {
                      "id_cupon": 1,
                      "codigo": "DESC15",
                      "descuento": 15,
                      "fecha_expiracion": "2026-01-31",
                      "cliente": { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "_links": {
                        "self":    { "href": "/api/v2/cupones/1" },
                        "cupones": { "href": "/api/v2/cupones" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cupón no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cupón con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<CuponDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CuponDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(cuponService.updateCupon(id, request)));
    }

    // DELETE /api/v2/cupones/{id}
    @Operation(summary = "Eliminar un cupón por ID", description = "Elimina permanentemente un cupón. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Cupón eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cupón no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cupón con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.noContent().build();
    }
}