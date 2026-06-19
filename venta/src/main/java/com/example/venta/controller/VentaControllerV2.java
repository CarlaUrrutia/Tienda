package com.example.venta.controller;

import com.example.venta.assembler.VentaModelAssembler;
import com.example.venta.dto.VentaDTO;
import com.example.venta.service.VentaService;
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
@RequestMapping("/api/v2/ventas")
@RequiredArgsConstructor
public class VentaControllerV2 {

    private final VentaService ventaService;
    private final VentaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<VentaDTO.Response>> listarTodos() {
        List<EntityModel<VentaDTO.Response>> ventas = ventaService.getAllVentas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ventas,
                linkTo(methodOn(VentaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<VentaDTO.Response> buscarPorId(@PathVariable Integer id) {
        VentaDTO.Response venta = ventaService.getVentaById(id);
        return assembler.toModel(venta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<VentaDTO.Response>> crear(@Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response nueva = ventaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(VentaControllerV2.class).buscarPorId(nueva.getId_venta())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<VentaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response actualizado = ventaService.updateVenta(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}