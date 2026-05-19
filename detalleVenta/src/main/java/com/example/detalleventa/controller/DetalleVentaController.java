package com.example.detalleVenta.controller;

import com.example.detalleVenta.model.DetalleVenta;
import com.example.detalleVenta.service.DetalleVentaService;
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
    public List<DetalleVenta> listar() {
        return detalleVentaService.getAllDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtenerPorId(@PathVariable Integer id) {
        DetalleVenta detalle = detalleVentaService.getDetalleById(id);
        return detalle != null ? ResponseEntity.ok(detalle) : ResponseEntity.notFound().build();
    }

    @GetMapping("/venta/{idVenta}")
    public List<DetalleVenta> obtenerPorVenta(@PathVariable Integer idVenta) {
        return detalleVentaService.getDetallesByVenta(idVenta);
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> crear(@RequestBody DetalleVenta detalle) {
        DetalleVenta nuevo = detalleVentaService.save(detalle);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(@PathVariable Integer id, @RequestBody DetalleVenta detalle) {
        DetalleVenta actualizado = detalleVentaService.updateDetalle(id, detalle);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
