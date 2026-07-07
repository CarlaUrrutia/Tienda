package com.example.cliente.controller;

import com.example.cliente.assembler.ClienteModelAssembler;
import com.example.cliente.dto.ApiResponse;
import com.example.cliente.dto.ClienteDTO;
import com.example.cliente.model.Cliente;
import com.example.cliente.service.ClienteService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Clientes V2", description = "Gestión de clientes con HATEOAS")
@RestController
@RequestMapping("/api/v2/clientes")
@RequiredArgsConstructor
public class ClienteControllerV2 {

    private final ClienteService clienteService;
    private final ClienteModelAssembler assembler;

    // GET /api/v2/clientes
    @Operation(summary = "Listar todos los clientes", description = "Retorna todos los clientes con links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Clientes obtenidos exitosamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Clientes obtenidos exitosamente",
                      "data": {
                        "_embedded": {
                          "responseList": [
                            {
                              "id_cliente": 1,
                              "nombre": "Juan",
                              "apellido": "Pérez",
                              "email": "juan@mail.com",
                              "_links": {
                                "self":     { "href": "/api/v2/clientes/1" },
                                "clientes": { "href": "/api/v2/clientes" }
                              }
                            }
                          ]
                        },
                        "_links": { "self": { "href": "/api/v2/clientes" } }
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<CollectionModel<EntityModel<ClienteDTO.Response>>>> listarTodos() {
        List<EntityModel<ClienteDTO.Response>> clientes = clienteService.getAllClientes().stream()
                .map(this::toResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ClienteDTO.Response>> collection = CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(
            ApiResponse.ok("Clientes obtenidos exitosamente", collection, null)
        );
    }

    // GET /api/v2/clientes/{id}
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Cliente encontrado",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Cliente encontrado exitosamente",
                      "data": {
                        "id_cliente": 1,
                        "nombre": "Juan",
                        "apellido": "Pérez",
                        "email": "juan@mail.com",
                        "_links": {
                          "self":     { "href": "/api/v2/clientes/1" },
                          "clientes": { "href": "/api/v2/clientes" }
                        }
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cliente con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<EntityModel<ClienteDTO.Response>>> buscarPorId(@PathVariable Integer id) {
        ClienteDTO.Response response = toResponse(clienteService.getClienteById(id));
        return ResponseEntity.ok(
            ApiResponse.ok("Cliente encontrado exitosamente", assembler.toModel(response), null)
        );
    }

    // POST /api/v2/clientes
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un cliente y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Cliente creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Cliente registrado correctamente",
                    value = """
                    {
                      "status": 201,
                      "success": true,
                      "message": "Cliente creado exitosamente",
                      "data": {
                        "id_cliente": 5,
                        "nombre": "María",
                        "apellido": "González",
                        "email": "maria@mail.com",
                        "_links": {
                          "self":     { "href": "/api/v2/clientes/5" },
                          "clientes": { "href": "/api/v2/clientes" }
                        }
                      },
                      "timestamp": "2025-06-22T10:00:00"
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
                        "nombre":   "Error nombre no valido",
                        "apellido": "Error apellido no valido",
                        "email":    "Error, email no valido"
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<EntityModel<ClienteDTO.Response>>> crear(@Valid @RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.save(cliente);
        ClienteDTO.Response response = toResponse(nuevo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(
                    "Cliente creado exitosamente",
                    assembler.toModel(response),
                    null
                ));
    }

    // PUT /api/v2/clientes/{id}
    @Operation(summary = "Actualizar un cliente existente", description = "Modifica los datos de un cliente por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cliente actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Cliente modificado correctamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Cliente actualizado exitosamente",
                      "data": {
                        "id_cliente": 1,
                        "nombre": "Juan Carlos",
                        "apellido": "Pérez",
                        "email": "jc@mail.com",
                        "_links": {
                          "self":     { "href": "/api/v2/clientes/1" },
                          "clientes": { "href": "/api/v2/clientes" }
                        }
                      },
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cliente con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<EntityModel<ClienteDTO.Response>>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        ClienteDTO.Response response = toResponse(clienteService.updateCliente(id, cliente));
        return ResponseEntity.ok(
            ApiResponse.ok("Cliente actualizado exitosamente", assembler.toModel(response), null)
        );
    }

    // DELETE /api/v2/clientes/{id}
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina permanentemente un cliente.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cliente eliminado exitosamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Eliminado",
                    summary = "Cliente borrado correctamente",
                    value = """
                    {
                      "status": 200,
                      "success": true,
                      "message": "Cliente con id 1 eliminado exitosamente",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "No encontrado",
                    summary = "ID inexistente",
                    value = """
                    {
                      "status": 404,
                      "success": false,
                      "message": "Cliente con id 99 no encontrado",
                      "timestamp": "2025-06-22T10:00:00"
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.ok(
            ApiResponse.deleted("Cliente con id " + id + " eliminado exitosamente", null)
        );
    }

    private ClienteDTO.Response toResponse(Cliente cliente) {
        return new ClienteDTO.Response(
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail()
        );
    }
}