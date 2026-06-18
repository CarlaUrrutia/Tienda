package com.example.empleado.controller;

import com.example.empleado.assembler.EmpleadoModelAssembler;
import com.example.empleado.dto.EmpleadoDTO;
import com.example.empleado.service.EmpleadoService;
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
@RequestMapping("/api/v2/empleados")
@RequiredArgsConstructor
public class EmpleadoControllerV2 {

    private final EmpleadoService empleadoService;
    private final EmpleadoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<EmpleadoDTO.Response>> listarTodos() {
        List<EntityModel<EmpleadoDTO.Response>> empleados = empleadoService.getAllEmpleados().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(empleados,
                linkTo(methodOn(EmpleadoControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<EmpleadoDTO.Response> buscarPorId(@PathVariable Integer id) {
        EmpleadoDTO.Response empleado = empleadoService.getEmpleadoById(id);
        return assembler.toModel(empleado);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EmpleadoDTO.Response>> crear(@Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response nuevo = empleadoService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(EmpleadoControllerV2.class).buscarPorId(nuevo.getId_empleado())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EmpleadoDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response actualizado = empleadoService.updateEmpleado(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}