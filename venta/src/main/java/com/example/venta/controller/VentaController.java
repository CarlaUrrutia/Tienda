package com.example.venta.controller;

import com.example.venta.model.Venta;
import com.example.venta.service.VentaService;
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
    public List<Venta> listar() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Integer id) {
        Venta venta = ventaService.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody Venta venta) {
        Venta nueva = ventaService.save(venta);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta actualizada = ventaService.updateVenta(id, venta);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
