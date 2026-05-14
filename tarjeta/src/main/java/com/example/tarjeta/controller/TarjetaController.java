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

public class TarjetaController {
    @Autowired
    private TarjetaService  tarjetaServi;

    @GetMapping
    public ResponseEntity<List<Tarjeta>> listar(){
        List<Tarjeta> tarjet = tarjetaServi.getAllTarjeta();
        if (tarjet.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarjet);
    }

    @PostMapping
    public ResponseEntity<Tarjeta> guardar(@RequestBody Tarjeta tar){
        Tarjeta tarj = tarjetaServi.createTarjeta(tar);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarj);
    }

    @DeleteMapping("/{id_tarjeta}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_tarjeta){
        try{
            tarjetaServi.deleteTarjeta(id_tarjeta);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_tarjeta}")
    public ResponseEntity<Tarjeta> buscarByid_tarjeta(@PathVariable
        int id_tarjeta){
            Tarjeta ta = tarjetaServi.getPersoByid_tarjeta(id_tarjeta);
            if (ta==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ta);
        }
}
