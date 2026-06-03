package com.example.rol.controller;

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
import com.example.gerente.model.Rol;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolService  rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> listar(){
        List<Rol> ro = rolService.getAllTarjeta();
        if (ro.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ro);
    }

    @PostMapping
    public ResponseEntity<Rol> guardar(@RequestBody Rol r){
        Rol ro = rolService.createRol(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(ro);
    }

    @DeleteMapping("/{id_rol}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_rol){
        try{
            rolService.deleteRol(id_rol);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_rol}")
    public ResponseEntity<Rol> buscarByid_rol(@PathVariable
        int id_rol){
            Rol rol = rolService.getPersoByid_rol(id_rol);
            if (rol==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(rol);
        }
}