package com.example.factura.controller;

import com.example.factura.DTO.FacturaDTO;
import com.example.factura.service.FacturaService;
import jakarta.validation.Valid;
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
    public List<FacturaDTO.Response> listar() {
        return facturaService.getAllFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        FacturaDTO.Response r = facturaService.getFacturaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FacturaDTO.Response> crear(@Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody FacturaDTO.Request request) {
        FacturaDTO.Response r = facturaService.updateFactura(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
