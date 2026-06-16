package com.example.detalleventa.controller;

import com.example.detalleventa.assembler.DetalleVentaModelAssembler;
import com.example.detalleventa.dto.DetalleVentaDTO;
import com.example.detalleventa.service.DetalleVentaService;
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
@RequestMapping("/api/v2/detalles")
@RequiredArgsConstructor
public class DetalleVentaControllerV2 {

    private final DetalleVentaService detalleVentaService;
    private final DetalleVentaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<DetalleVentaDTO.Response>> listarTodos() {
        List<EntityModel<DetalleVentaDTO.Response>> detalles = detalleVentaService.getAllDetalles().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(detalles,
                linkTo(methodOn(DetalleVentaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<DetalleVentaDTO.Response> buscarPorId(@PathVariable Integer id) {
        DetalleVentaDTO.Response detalle = detalleVentaService.getDetalleById(id);
        return assembler.toModel(detalle);
    }

    @GetMapping(value = "/venta/{idVenta}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<DetalleVentaDTO.Response>> buscarPorVenta(@PathVariable Integer idVenta) {
        List<EntityModel<DetalleVentaDTO.Response>> detalles = detalleVentaService.getDetallesByVenta(idVenta).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(detalles,
                linkTo(methodOn(DetalleVentaControllerV2.class).buscarPorVenta(idVenta)).withSelfRel());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DetalleVentaDTO.Response>> crear(@Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response nuevo = detalleVentaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(DetalleVentaControllerV2.class).buscarPorId(nuevo.getId_detalle())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DetalleVentaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response actualizado = detalleVentaService.updateDetalle(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}