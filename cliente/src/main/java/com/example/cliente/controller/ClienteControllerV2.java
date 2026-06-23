package com.example.cliente.controller;

import com.example.cliente.assembler.ClienteModelAssembler;
import com.example.cliente.dto.ClienteDTO;
import com.example.cliente.model.Cliente;
import com.example.cliente.service.ClienteService;
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

@RestController
@RequestMapping("/api/v2/clientes")
@RequiredArgsConstructor
public class ClienteControllerV2 {

    private final ClienteService clienteService;
    private final ClienteModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<ClienteDTO.Response>> listarTodos() {
        List<EntityModel<ClienteDTO.Response>> clientes = clienteService.getAllClientes().stream()
                .map(this::toResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<ClienteDTO.Response> buscarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.getClienteById(id);
        return assembler.toModel(toResponse(cliente));
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ClienteDTO.Response>> crear(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.save(cliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).buscarPorId(nuevo.getId_cliente())).toUri())
                .body(assembler.toModel(toResponse(nuevo)));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ClienteDTO.Response>> actualizar(
            @PathVariable Integer id,
            @RequestBody Cliente cliente) {
        Cliente actualizado = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(assembler.toModel(toResponse(actualizado)));
    }

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