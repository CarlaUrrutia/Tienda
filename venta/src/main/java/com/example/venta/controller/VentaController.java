package com.example.venta.controller;

import com.example.venta.DTO.VentaDTO;
import com.example.venta.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDTO.Response> listar() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        VentaDTO.Response venta = ventaService.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VentaDTO.Response> crear(@Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response nueva = ventaService.save(request);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody VentaDTO.Request request) {
        VentaDTO.Response actualizada = ventaService.updateVenta(id, request);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
