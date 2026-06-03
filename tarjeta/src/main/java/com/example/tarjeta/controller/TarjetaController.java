package com.example.tarjeta.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.Service.RegionService;
import com.example.gerente.Service.TarjetaService;
import com.example.gerente.model.Tarjeta;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {
    @Autowired private TarjetaService tarjetaService;

    @GetMapping
    public List<TarjetaDTO.Response> listar() { return tarjetaService.getAllTarjetas(); }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        TarjetaDTO.Response r = tarjetaService.getTarjetaById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TarjetaDTO.Response> crear(@Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody TarjetaDTO.Request request) {
        TarjetaDTO.Response r = tarjetaService.updateTarjeta(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tarjetaService.delete(id);
        return ResponseEntity.ok().build();
    }
}