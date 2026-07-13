package com.example.proveedor.controller;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Proveedores", description = "Gestión de proveedores")
@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "Operaciones CRUD sobre proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    private void agregarLinks(Proveedor p) {
        p.add(linkTo(methodOn(ProveedorController.class).obtenerPorId(p.getId_proveedor())).withSelfRel());
        p.add(linkTo(methodOn(ProveedorController.class).listar()).withRel("proveedores"));
        p.add(linkTo(methodOn(ProveedorController.class).actualizar(p.getId_proveedor(), null)).withRel("actualizar"));
        p.add(linkTo(methodOn(ProveedorController.class).eliminar(p.getId_proveedor())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public CollectionModel<Proveedor> listar() {
        var proveedores = proveedorService.getAllProveedores();
        proveedores.forEach(this::agregarLinks);
        return CollectionModel.of(proveedores, linkTo(methodOn(ProveedorController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un proveedor por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Integer id) {
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if (proveedor == null) return ResponseEntity.notFound().build();
        agregarLinks(proveedor);
        return ResponseEntity.ok(proveedor);
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor) {
        Proveedor nuevo = proveedorService.save(proveedor);
        if (nuevo == null) return ResponseEntity.badRequest().build();
        agregarLinks(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.updateProveedor(id, proveedor);
        if (actualizado == null) return ResponseEntity.notFound().build();
        agregarLinks(actualizado);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un proveedor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.delete(id);
        return ResponseEntity.ok().build();
    }
}