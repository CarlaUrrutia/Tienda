package com.example.gerente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerente.model.Empleado;
import com.example.gerente.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listar(){
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Empleado> obtenerPorId(@PathVariable Integer id){
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if(empleado!= null){
            return ResponseEntity.ok(empleado);
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping  
    public ResponseEntity<Empleado> save(@RequestBody Empleado empl){
        Empleado nuevo = empleadoService.save(empl);
        if(nuevo!=null){
            return ResponseEntity.ok(nuevo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> modificar(@PathVariable Integer id, @RequestBody Empleado e) {
        Empleado actua = empleadoService.updateEmpleado(id, e);
        
        if (actua != null) {
            return ResponseEntity.ok(actua); 
        }
        return ResponseEntity.notFound().build(); 

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        empleadoService.delete(id);
        return ResponseEntity.ok().build();
    }


}
