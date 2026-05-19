package com.example.empleado.controller;

import com.example.empleado.model.Empleado;
import com.example.empleado.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        return empleado != null ? ResponseEntity.ok(empleado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        Empleado nuevo = empleadoService.save(empleado);
        return nuevo != null ? ResponseEntity.ok(nuevo) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Empleado actualizado = empleadoService.updateEmpleado(id, empleado);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
