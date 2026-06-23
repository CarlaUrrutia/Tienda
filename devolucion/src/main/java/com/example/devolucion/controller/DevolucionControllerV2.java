package com.example.devolucion.controller;

import com.example.devolucion.assembler.DevolucionModelAssembler;
import com.example.devolucion.dto.DevolucionDTO;
import com.example.devolucion.service.DevolucionService;
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
@RequestMapping("/api/v2/devoluciones")
@RequiredArgsConstructor
public class DevolucionControllerV2 {

    private final DevolucionService devolucionService;
    private final DevolucionModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<DevolucionDTO.Response>> listarTodos() {
        List<EntityModel<DevolucionDTO.Response>> devoluciones = devolucionService.getAllDevoluciones().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(devoluciones,
                linkTo(methodOn(DevolucionControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<DevolucionDTO.Response> buscarPorId(@PathVariable Integer id) {
        DevolucionDTO.Response devolucion = devolucionService.getDevolucionById(id);
        return assembler.toModel(devolucion);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DevolucionDTO.Response>> crear(@Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response nueva = devolucionService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(DevolucionControllerV2.class).buscarPorId(nueva.getId_devolucion())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<DevolucionDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response actualizado = devolucionService.updateDevolucion(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}