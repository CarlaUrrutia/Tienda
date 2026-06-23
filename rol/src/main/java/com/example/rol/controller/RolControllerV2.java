package com.example.rol.controller;

import com.example.rol.assembler.RolModelAssembler;
import com.example.rol.dto.RolDTO;
import com.example.rol.service.RolService;
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
@RequestMapping("/api/v2/roles")
@RequiredArgsConstructor
public class RolControllerV2 {

    private final RolService rolService;
    private final RolModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<RolDTO.Response>> listarTodos() {
        List<EntityModel<RolDTO.Response>> roles = rolService.getAllRoles().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(roles,
                linkTo(methodOn(RolControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<RolDTO.Response> buscarPorId(@PathVariable Integer id) {
        RolDTO.Response rol = rolService.getRolById(id);
        return assembler.toModel(rol);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<RolDTO.Response>> crear(@Valid @RequestBody RolDTO.Request request) {
        RolDTO.Response nuevo = rolService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(RolControllerV2.class).buscarPorId(nuevo.getId_rol())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<RolDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RolDTO.Request request) {
        RolDTO.Response actualizado = rolService.updateRol(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}