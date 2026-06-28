package com.example.cliente.controller;

import com.example.cliente.assembler.ClienteModelAssembler;
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
                schema = @Schema(implementation = CollectionModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Clientes obtenidos exitosamente",
                    value = """
                    {
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
                      "_links": {
                        "self": { "href": "/api/v2/clientes" }
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<ClienteDTO.Response>> listarTodos() {
        List<EntityModel<ClienteDTO.Response>> clientes = clienteService.getAllClientes().stream()
                .map(this::toResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).listarTodos()).withSelfRel());
    }

    // GET /api/v2/clientes/{id}
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente específico con sus links HATEOAS.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Éxito",
                    summary = "Cliente encontrado",
                    value = """
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
    public ResponseEntity<EntityModel<ClienteDTO.Response>> buscarPorId(@PathVariable Integer id) {
        ClienteDTO.Response response = toResponse(clienteService.getClienteById(id));
        return ResponseEntity.ok(assembler.toModel(response));
    }

    // POST /api/v2/clientes
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un cliente y retorna su ubicación en el header Location.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Cliente creado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Creado",
                    summary = "Cliente registrado correctamente",
                    value = """
                    {
                      "id_cliente": 5,
                      "nombre": "María",
                      "apellido": "González",
                      "email": "maria@mail.com",
                      "_links": {
                        "self":     { "href": "/api/v2/clientes/5" },
                        "clientes": { "href": "/api/v2/clientes" }
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
    public ResponseEntity<EntityModel<ClienteDTO.Response>> crear(@Valid @RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.save(cliente);
        ClienteDTO.Response response = toResponse(nuevo);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).buscarPorId(nuevo.getId_cliente())).toUri())
                .body(assembler.toModel(response));
    }

    // PUT /api/v2/clientes/{id}
    @Operation(summary = "Actualizar un cliente existente", description = "Modifica los datos de un cliente por su ID.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Cliente actualizado exitosamente",
            content = @Content(
                mediaType = "application/hal+json",
                schema = @Schema(implementation = EntityModel.class),
                examples = @ExampleObject(
                    name = "Actualizado",
                    summary = "Cliente modificado correctamente",
                    value = """
                    {
                      "id_cliente": 1,
                      "nombre": "Juan Carlos",
                      "apellido": "Pérez",
                      "email": "jc@mail.com",
                      "_links": {
                        "self":     { "href": "/api/v2/clientes/1" },
                        "clientes": { "href": "/api/v2/clientes" }
                      }
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
    public ResponseEntity<EntityModel<ClienteDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        ClienteDTO.Response response = toResponse(clienteService.updateCliente(id, cliente));
        return ResponseEntity.ok(assembler.toModel(response));
    }

    // DELETE /api/v2/clientes/{id}
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina permanentemente un cliente. Retorna 204 sin contenido si fue exitoso.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Cliente eliminado exitosamente — sin contenido en la respuesta"
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
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
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