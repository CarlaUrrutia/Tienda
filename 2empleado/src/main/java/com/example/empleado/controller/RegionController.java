package com.example.gerente.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.Service.ProveedorService;
import com.example.gerente.Service.RegionService;
import com.example.gerente.model.Oferta;
import com.example.gerente.model.Producto;
import com.example.gerente.model.Proveedor;
import com.example.gerente.model.Region;

public class RegionController {
    @Autowired
    private RegionService  RegionServi;

    @GetMapping
    public ResponseEntity<List<Region>> listar(){
        List<Region> r = RegionServi.getAllRegion();
        if (r.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(r);
    }

    @PostMapping
    public ResponseEntity<Region> guardar(@RequestBody Region reg){
        Region re = RegionServi.createRegion(reg);
        return ResponseEntity.status(HttpStatus.CREATED).body(re);
    }

    @DeleteMapping("/{id_region}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_region){
        try{
            RegionServi.deleteRegion(id_region);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_region}")
    public ResponseEntity<Region> buscarByid_region(@PathVariable
        int id_region){
            Region regi = RegionServi.getPersoByid_region(id_region);
            if (regi==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(regi);
        }
}
