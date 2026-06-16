package com.example.cupon.controller;

import com.example.cupon.dto.CuponDTO;
import com.example.cupon.service.CuponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {
    @Autowired private CuponService cuponService;

    @GetMapping
    public List<CuponDTO.Response> listar() { return cuponService.getAllCupones(); }

    @GetMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> obtenerPorId(@PathVariable Integer id) {
        CuponDTO.Response r = cuponService.getCuponById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CuponDTO.Response> crear(@Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuponDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody CuponDTO.Request request) {
        CuponDTO.Response r = cuponService.updateCupon(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuponService.delete(id);
        return ResponseEntity.ok().build();
    }
}
