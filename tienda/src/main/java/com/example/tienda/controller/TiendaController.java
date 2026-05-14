package com.example.gerente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.Service.TarjetaService;
import com.example.gerente.Service.TiendaService;
import com.example.gerente.model.Tarjeta;
import com.example.gerente.model.Tienda;

public class TiendaController {
    @Autowired
    private TiendaService  tiendaServi;

    @GetMapping
    public ResponseEntity<List<Tienda>> listar(){
        List<Tienda> ti = tiendaServi.getAllTienda();
        if (ti.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ti);
    }

    @PostMapping
    public ResponseEntity<Tarjeta> guardar(@RequestBody Tienda tie){
        Tienda tien = tiendaServi.createTienda(tie);
        return ResponseEntity.status(HttpStatus.CREATED).body(tien);
    }

    @DeleteMapping("/{id_tienda}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_tienda){
        try{
            tiendaServi.deleteTarjeta(id_tienda);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_tienda}")
    public ResponseEntity<Tarjeta> buscarByid_tienda(@PathVariable
        int id_tienda){
            tienda tiend = tiendaServi.getPersoByid_tienda(id_tienda);
            if (tiend==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(tiend);
        }
}
