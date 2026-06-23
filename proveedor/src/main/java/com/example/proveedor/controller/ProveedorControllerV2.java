package com.example.proveedor.controller;

import com.example.proveedor.assembler.ProveedorModelAssembler;
import com.example.proveedor.dto.ProveedorDTO;
import com.example.proveedor.service.ProveedorService;
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
@RequestMapping("/api/v2/proveedores")
@RequiredArgsConstructor
public class ProveedorControllerV2 {

    private final ProveedorService proveedorService;
    private final ProveedorModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<ProveedorDTO.Response>> listarTodos() {
        List<EntityModel<ProveedorDTO.Response>> proveedores = proveedorService.getAllProveedores().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<ProveedorDTO.Response> buscarPorId(@PathVariable Integer id) {
        ProveedorDTO.Response proveedor = proveedorService.getProveedorById(id);
        return assembler.toModel(proveedor);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ProveedorDTO.Response>> crear(@Valid @RequestBody ProveedorDTO.Request request) {
        ProveedorDTO.Response nuevo = proveedorService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(ProveedorControllerV2.class).buscarPorId(nuevo.getId_proveedor())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<ProveedorDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProveedorDTO.Request request) {
        ProveedorDTO.Response actualizado = proveedorService.updateProveedor(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}