package com.example.proveedor.controller;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Proveedores", description = "Gestión de proveedores")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Listar todos los proveedores")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.getAllProveedores();
    }

    @Operation(summary = "Obtener proveedor por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Integer id) {
        Proveedor proveedor = proveedorService.getProveedorById(id);
        return proveedor != null ? ResponseEntity.ok(proveedor) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Proveedor creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor) {
        Proveedor nuevo = proveedorService.save(proveedor);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.updateProveedor(id, proveedor);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un proveedor por ID")
    @ApiResponse(responseCode = "200", description = "Proveedor eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
