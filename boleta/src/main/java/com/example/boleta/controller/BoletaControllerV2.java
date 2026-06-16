package com.example.boleta.controller;

import com.example.boleta.assembler.BoletaModelAssembler;
import com.example.boleta.dto.BoletaDTO;
import com.example.boleta.service.BoletaService;
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
@RequestMapping("/api/v2/boletas")
@RequiredArgsConstructor
public class BoletaControllerV2 {

    private final BoletaService boletaService;
    private final BoletaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<BoletaDTO.Response>> listarTodos() {
        List<EntityModel<BoletaDTO.Response>> boletas = boletaService.getAllBoletas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(boletas,
                linkTo(methodOn(BoletaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<BoletaDTO.Response> buscarPorId(@PathVariable Integer id) {
        BoletaDTO.Response boleta = boletaService.getBoletaById(id);
        return assembler.toModel(boleta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BoletaDTO.Response>> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(BoletaControllerV2.class).buscarPorId(nueva.getId_boleta())).toUri())
                .body(assembler.toModel(nueva));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boletaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}