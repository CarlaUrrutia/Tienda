package com.example.tarjeta.controller;

import com.example.tarjeta.assembler.TarjetaModelAssembler;
import com.example.tarjeta.dto.TarjetaDTO;
import com.example.tarjeta.service.TarjetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/tarjetas")
@RequiredArgsConstructor
public class TarjetaControllerV2 {

    private final TarjetaService tarjetaService;
    private final TarjetaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<TarjetaDTO.Response>> listarTodos() {
        List<EntityModel<TarjetaDTO.Response>> tarjetas = tarjetaService.getAllTarjetas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarjetas,
                linkTo(methodOn(TarjetaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<TarjetaDTO.Response> buscarPorId(@PathVariable Integer id) {
        TarjetaDTO.Response tarjeta = tarjetaService.getTarjetaById(id);
        return assembler.toModel(tarjeta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TarjetaDTO.Response>> crear(@Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response nueva = tarjetaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(TarjetaControllerV2.class).buscarPorId(nueva.getId_tarjeta())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TarjetaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response actualizado = tarjetaService.updateTarjeta(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}