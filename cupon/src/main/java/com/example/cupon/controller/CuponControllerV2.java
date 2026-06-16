package com.example.cupon.controller;

import com.example.cupon.dto.CuponDTO;
import com.example.cupon.assembler.CuponModelAssembler;
import com.example.cupon.service.CuponService;
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
@RequestMapping("/api/v2/cupones")
@RequiredArgsConstructor
public class CuponControllerV2 {

    private final CuponService cuponService;
    private final CuponModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<CuponDTO.Response>> listarTodos() {
        List<EntityModel<CuponDTO.Response>> cupones = cuponService.getAllCupones().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cupones,
                linkTo(methodOn(CuponControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<CuponDTO.Response> buscarPorId(@PathVariable Integer id) {
        CuponDTO.Response cupon = cuponService.getCuponById(id);
        return assembler.toModel(cupon);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CuponDTO.Response>> crear(@Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response nuevo = cuponService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(CuponControllerV2.class).buscarPorId(nuevo.getId_cupon())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CuponDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response actualizado = cuponService.updateCupon(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.noContent().build();
    }
}