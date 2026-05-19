package com.example.factura.controller;

import com.example.factura.model.Factura;
import com.example.factura.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> listar() {
        return facturaService.getAllFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Integer id) {
        Factura factura = facturaService.getFacturaById(id);
        return factura != null ? ResponseEntity.ok(factura) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody Factura factura) {
        Factura nueva = facturaService.save(factura);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Integer id, @RequestBody Factura factura) {
        Factura actualizada = facturaService.updateFactura(id, factura);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
