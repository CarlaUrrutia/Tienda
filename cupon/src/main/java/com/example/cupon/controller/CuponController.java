package com.example.cupon.controller;

import com.example.cupon.model.Cupon;
import com.example.cupon.service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @GetMapping
    public List<Cupon> listar() {
        return cuponService.getAllCupones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupon> obtenerPorId(@PathVariable Integer id) {
        Cupon cupon = cuponService.getCuponById(id);
        return cupon != null ? ResponseEntity.ok(cupon) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cupon> crear(@RequestBody Cupon cupon) {
        Cupon nuevo = cuponService.save(cupon);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cupon> actualizar(@PathVariable Integer id, @RequestBody Cupon cupon) {
        Cupon actualizado = cuponService.updateCupon(id, cupon);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.ok().build();
    }
}
