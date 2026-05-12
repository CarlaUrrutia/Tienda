package com.example.gerente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.model.Factura;
import com.example.gerente.model.Inventario;

public class InventarioController {
    @Autowired
    private InventarioService  inventarioServi;

    @GetMapping
    public ResponseEntity<List<Inventario>> listar(){
        List<Inventario> inveta = inventarioServi.getAllInventario();
        if (inveta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inveta);
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inven){
        Inventario in = inventarioServi.createInventario(inven);
        return ResponseEntity.status(HttpStatus.CREATED).body(in);
    }

    @DeleteMapping("/{id_inventario}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_inventario){
        try{
            inventarioServi.deleteInventario(id_inventario);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_inventario}")
    public ResponseEntity<Inventario> buscarByid_inventario(@PathVariable
        int id_inventario){
            Inventario inv = inventarioServi.getPersoByid_inventario(id_inventario);
            if (inv==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(inv);
        }
}
