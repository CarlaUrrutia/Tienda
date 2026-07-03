package com.example.envio.controller;

import com.example.envio.assembler.EnvioModelAssembler;
import com.example.envio.dto.EnvioDTO;
import com.example.envio.service.EnvioService;
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

@Tag(name = "Envíos V2", description = "Gestión de envíos con HATEOAS")
@RestController
@RequestMapping("/api/v2/envios")
@RequiredArgsConstructor
public class EnvioControllerV2 {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    // GET /api/v2/envios
    @Operation(summary = "Listar todos los envíos", description = "Retorna todos los envíos con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Envíos obtenidos exitosamente",
                    value = """
                    {
                      "_embedded": {
                        "responseList": [
                          {
                            "id_envio": 1,
                            "fecha_envio": "2025-06-01",
                            "fecha_estimada_entrega": "2025-06-05",
                            "estado": "En tránsito",
                            "direccion_destino": "Av. Providencia 123, Santiago",
                            "venta":    { "id_venta": 1, "fecha_venta": "2025-05-30", "id_cliente": 1, "id_empleado": 2 },
                            "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                            "empleado": { "id_empleado": 2, "nombre": "Ana María", "apellido": "López Soto", "sueldo": 500000, "id_tienda": 1 },
                            "id_ciudad": 1,
                            "_links": {
                              "self":   { "href": "/api/v2/envios/1" },
                              "envios": { "href": "/api/v2/envios" }
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": { "href": "/api/v2/envios" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<EnvioDTO.Response>> listarTodos() {
        List<EntityModel<EnvioDTO.Response>> envios = envioService.getAllEnvios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/envios/{id}
    @Operation(summary = "Obtener envío por ID", description = "Retorna un envío específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Envío encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Envío encontrado",
                    value = """
                    {
                      "id_envio": 1,
                      "fecha_envio": "2025-06-01",
                      "fecha_estimada_entrega": "2025-06-05",
                      "estado": "En tránsito",
                      "direccion_destino": "Av. Providencia 123, Santiago",
                      "venta":    { "id_venta": 1, "fecha_venta": "2025-05-30", "id_cliente": 1, "id_empleado": 2 },
                      "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "empleado": { "id_empleado": 2, "nombre": "Ana María", "apellido": "López Soto", "sueldo": 500000, "id_tienda": 1 },
                      "id_ciudad": 1,
                      "_links": {
                        "self":   { "href": "/api/v2/envios/1" },
                        "envios": { "href": "/api/v2/envios" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Envío no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Envío con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EnvioDTO.Response>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(envioService.getEnvioById(id)));
    }

    // POST /api/v2/envios
    @Operation(summary = "Crear un nuevo envío", description = "Registra un envío y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Envío creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Envío registrado correctamente",
                    value = """
                    {
                      "id_envio": 5,
                      "fecha_envio": "2025-06-22",
                      "fecha_estimada_entrega": "2025-06-27",
                      "estado": "Pendiente",
                      "direccion_destino": "Calle Falsa 123, Valparaíso",
                      "venta":    { "id_venta": 3, "fecha_venta": "2025-06-20", "id_cliente": 2, "id_empleado": 1 },
                      "cliente":  { "id_cliente": 2, "nombre": "María", "apellido": "González", "email": "maria@mail.com" },
                      "empleado": { "id_empleado": 1, "nombre": "Carlos Alberto", "apellido": "Soto Muñoz", "sueldo": 600000, "id_tienda": 1 },
                      "id_ciudad": 2,
                      "_links": {
                        "self":   { "href": "/api/v2/envios/5" },
                        "envios": { "href": "/api/v2/envios" }
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
                        "estado":             "El estado es obligatorio",
                        "direccion_destino":  "La direccion es obligatoria",
                        "id_venta":           "El id_venta es obligatorio"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EnvioDTO.Response>> crear(@Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response nuevo = envioService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).buscarPorId(nuevo.getId_envio())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT /api/v2/envios/{id}
    @Operation(summary = "Actualizar un envío existente", description = "Modifica el estado y dirección destino de un envío por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Envío actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Envío modificado correctamente",
                    value = """
                    {
                      "id_envio": 1,
                      "fecha_envio": "2025-06-01",
                      "fecha_estimada_entrega": "2025-06-05",
                      "estado": "Entregado",
                      "direccion_destino": "Av. Providencia 123, Santiago",
                      "venta":    { "id_venta": 1, "fecha_venta": "2025-05-30", "id_cliente": 1, "id_empleado": 2 },
                      "cliente":  { "id_cliente": 1, "nombre": "Juan", "apellido": "Pérez", "email": "juan@mail.com" },
                      "empleado": { "id_empleado": 2, "nombre": "Ana María", "apellido": "López Soto", "sueldo": 500000, "id_tienda": 1 },
                      "id_ciudad": 1,
                      "_links": {
                        "self":   { "href": "/api/v2/envios/1" },
                        "envios": { "href": "/api/v2/envios" }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Envío no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Envío con id 99 no encontrado para actualizar",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EnvioDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EnvioDTO.Request request) {
        return ResponseEntity.ok(assembler.toModel(envioService.updateEnvio(id, request)));
    }

    // DELETE /api/v2/envios/{id}
    @Operation(summary = "Eliminar un envío por ID", description = "Elimina permanentemente un envío. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Envío eliminado exitosamente — sin contenido en la respuesta"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Envío no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Envío con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}