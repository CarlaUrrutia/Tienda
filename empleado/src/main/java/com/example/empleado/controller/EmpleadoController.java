package com.example.empleado.controller;

import com.example.empleado.DTO.EmpleadoDTO;
import com.example.empleado.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "Operaciones CRUD sobre empleados")
public class EmpleadoController {
    @Autowired private EmpleadoService empleadoService;

    private void agregarLinks(EmpleadoDTO.Response r) {
        r.add(linkTo(methodOn(EmpleadoController.class).obtenerPorId(r.getId_empleado())).withSelfRel());
        r.add(linkTo(methodOn(EmpleadoController.class).listar()).withRel("empleados"));
        r.add(linkTo(methodOn(EmpleadoController.class).actualizar(r.getId_empleado(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(EmpleadoController.class).eliminar(r.getId_empleado())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los empleados")
    @GetMapping
    public CollectionModel<EmpleadoDTO.Response> listar() {
        var empleados = empleadoService.getAllEmpleados();
        empleados.forEach(this::agregarLinks);
        return CollectionModel.of(empleados, linkTo(methodOn(EmpleadoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un empleado por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EmpleadoDTO.Response r = empleadoService.getEmpleadoById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear un nuevo empleado")
    @PostMapping
    public ResponseEntity<EmpleadoDTO.Response> crear(@Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar un empleado existente")
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.updateEmpleado(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar un empleado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.ok().build();
    }
}