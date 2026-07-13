package com.example.empleado.controller;

import com.example.empleado.dto.EmpleadoDTO;
import com.example.empleado.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Empleados", description = "Gestión de empleados")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Listar todos los empleados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<EmpleadoDTO.Response> listar() {
        return empleadoService.getAllEmpleados();
    }

    @Operation(summary = "Obtener empleado por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EmpleadoDTO.Response r = empleadoService.getEmpleadoById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo empleado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empleado creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<EmpleadoDTO.Response> crear(@Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un empleado existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empleado actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.updateEmpleado(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un empleado por ID")
    @ApiResponse(responseCode = "200", description = "Empleado eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
