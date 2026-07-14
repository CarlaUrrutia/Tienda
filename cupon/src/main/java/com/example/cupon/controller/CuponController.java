package com.example.cupon.controller;

import com.example.cupon.DTO.CuponDTO;
import com.example.cupon.service.CuponService;
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
@RequestMapping("/api/cupones")
@Tag(name = "Cupones", description = "Operaciones CRUD sobre cupones")
public class CuponController {
    @Autowired private CuponService cuponService;

    private void agregarLinks(CuponDTO.Response r) {
        r.add(linkTo(methodOn(CuponController.class).obtenerPorId(r.getId_cupon())).withSelfRel());
        r.add(linkTo(methodOn(CuponController.class).listar()).withRel("cupones"));
        r.add(linkTo(methodOn(CuponController.class).actualizar(r.getId_cupon(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(CuponController.class).eliminar(r.getId_cupon())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los cupones")
    @GetMapping
    public CollectionModel<CuponDTO.Response> listar() {
        var cupones = cuponService.getAllCupones();
        cupones.forEach(this::agregarLinks);
        return CollectionModel.of(cupones, linkTo(methodOn(CuponController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un cupón por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupón encontrado"),
            @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> obtenerPorId(@PathVariable Integer id) {
        CuponDTO.Response r = cuponService.getCuponById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear un nuevo cupón")
    @PostMapping
    public ResponseEntity<CuponDTO.Response> crear(@Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar un cupón existente")
    @PutMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.updateCupon(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar un cupón")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.ok().build();
    }
}