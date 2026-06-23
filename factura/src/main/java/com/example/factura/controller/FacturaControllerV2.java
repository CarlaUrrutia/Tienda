package com.example.factura.controller;

import com.example.factura.assembler.FacturaModelAssembler;
import com.example.factura.dto.FacturaDTO;
import com.example.factura.service.FacturaService;
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
@RequestMapping("/api/v2/facturas")
@RequiredArgsConstructor
public class FacturaControllerV2 {

    private final FacturaService facturaService;
    private final FacturaModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<FacturaDTO.Response>> listarTodos() {
        List<EntityModel<FacturaDTO.Response>> facturas = facturaService.getAllFacturas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(facturas,
                linkTo(methodOn(FacturaControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<FacturaDTO.Response> buscarPorId(@PathVariable Integer id) {
        FacturaDTO.Response factura = facturaService.getFacturaById(id);
        return assembler.toModel(factura);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<FacturaDTO.Response>> crear(@Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response nueva = facturaService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(FacturaControllerV2.class).buscarPorId(nueva.getId_factura())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<FacturaDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response actualizado = facturaService.updateFactura(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}