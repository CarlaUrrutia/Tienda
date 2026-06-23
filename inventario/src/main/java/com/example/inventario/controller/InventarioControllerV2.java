package com.example.inventario.controller;

import com.example.inventario.assembler.InventarioModelAssembler;
import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.service.InventarioService;
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

@RestController
@RequestMapping("/api/v2/inventarios")
@RequiredArgsConstructor
public class InventarioControllerV2 {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<InventarioDTO.Response>> listarTodos() {
        List<EntityModel<InventarioDTO.Response>> inventarios = inventarioService.getAllInventarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<InventarioDTO.Response> buscarPorId(@PathVariable Integer id) {
        InventarioDTO.Response inventario = inventarioService.getInventarioById(id);
        return assembler.toModel(inventario);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<InventarioDTO.Response>> crear(@Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response nuevo = inventarioService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).buscarPorId(nuevo.getId_inventario())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<InventarioDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody InventarioDTO.Request request) {
        InventarioDTO.Response actualizado = inventarioService.updateInventario(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}