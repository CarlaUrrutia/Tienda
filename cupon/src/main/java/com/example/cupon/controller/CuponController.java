package com.example.cupon.controller;

import com.example.cupon.DTO.CuponDTO;
import com.example.cupon.service.CuponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cupones", description = "Gestión de cupones de descuento")
@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @Operation(summary = "Listar todos los cupones")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<CuponDTO.Response> listar() {
        return cuponService.getAllCupones();
    }

    @Operation(summary = "Obtener cupón por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cupón encontrado"),
        @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> obtenerPorId(@PathVariable Integer id) {
        CuponDTO.Response r = cuponService.getCuponById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo cupón")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cupón creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<CuponDTO.Response> crear(@Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un cupón existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cupón actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.updateCupon(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un cupón por ID")
    @ApiResponse(responseCode = "200", description = "Cupón eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.ok().build();
    }
}
