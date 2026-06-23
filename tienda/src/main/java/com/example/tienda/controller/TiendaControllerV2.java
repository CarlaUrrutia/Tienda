package com.example.tienda.controller;

import com.example.tienda.assembler.TiendaModelAssembler;
import com.example.tienda.dto.TiendaDTO;
import com.example.tienda.service.TiendaService;
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
@RequestMapping("/api/v2/tiendas")
@RequiredArgsConstructor
public class TiendaControllerV2 {

    private final TiendaService tiendaService;
    private final TiendaModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<TiendaDTO.Response>> listarTodos() {
        List<EntityModel<TiendaDTO.Response>> tiendas = tiendaService.getAllTiendas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tiendas,
                linkTo(methodOn(TiendaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<TiendaDTO.Response> buscarPorId(@PathVariable Integer id) {
        TiendaDTO.Response tienda = tiendaService.getTiendaById(id);
        return assembler.toModel(tienda);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TiendaDTO.Response>> crear(@Valid @RequestBody TiendaDTO.Request request) {
        TiendaDTO.Response nueva = tiendaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(TiendaControllerV2.class).buscarPorId(nueva.getId_tienda())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<TiendaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TiendaDTO.Request request) {
        TiendaDTO.Response actualizado = tiendaService.updateTienda(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tiendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}