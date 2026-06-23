package com.example.oferta.controller;

import com.example.oferta.assembler.OfertaModelAssembler;
import com.example.oferta.dto.OfertaDTO;
import com.example.oferta.service.OfertaService;
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
@RequestMapping("/api/v2/ofertas")
@RequiredArgsConstructor
public class OfertaControllerV2 {

    private final OfertaService ofertaService;
    private final OfertaModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<OfertaDTO.Response>> listarTodos() {
        List<EntityModel<OfertaDTO.Response>> ofertas = ofertaService.getAllOfertas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ofertas,
                linkTo(methodOn(OfertaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<OfertaDTO.Response> buscarPorId(@PathVariable Integer id) {
        OfertaDTO.Response oferta = ofertaService.getOfertaById(id);
        return assembler.toModel(oferta);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<OfertaDTO.Response>> crear(@Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response nueva = ofertaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(OfertaControllerV2.class).buscarPorId(nueva.getId_oferta())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<OfertaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OfertaDTO.Request request) {
        OfertaDTO.Response actualizado = ofertaService.updateOferta(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}