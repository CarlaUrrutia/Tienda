package com.example.detalleventa.controller;

import com.example.detalleventa.DTO.DetalleVentaDTO;
import com.example.detalleventa.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVentaDTO.Response> listar() {
        return detalleVentaService.getAllDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DetalleVentaDTO.Response r = detalleVentaService.getDetalleById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @GetMapping("/venta/{idVenta}")
    public List<DetalleVentaDTO.Response> obtenerPorVenta(@PathVariable Integer idVenta) {
        return detalleVentaService.getDetallesByVenta(idVenta);
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDTO.Response> crear(@Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleVentaDTO.Request request) {
        DetalleVentaDTO.Response r = detalleVentaService.updateDetalle(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
