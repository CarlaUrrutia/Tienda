package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.model.Inventario;
import com.example.gerente.model.Oferta;

public class OfertaController {
    @Autowired
    private OfertaService  ofertaServi;

    @GetMapping
    public ResponseEntity<List<Oferta>> listar(){
        List<Oferta> ofe = inventarioServi.getAllOferta();
        if (ofe.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ofe);
    }

    @PostMapping
    public ResponseEntity<Oferta> guardar(@RequestBody Oferta ofe){
        Oferta of = ofertaServi.createOferta(ofe);
        return ResponseEntity.status(HttpStatus.CREATED).body(of);
    }

    @DeleteMapping("/{id_oferta}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_oferta){
        try{
            ofertaServi.deleteOferta(id_oferta);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_oferta}")
    public ResponseEntity<Inventario> buscarByid_oferta(@PathVariable
        int id_oferta){
            Oferta ofert = ofertaServi.getPersoByid_oferta(id_oferta);
            if (ofert==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ofert);
        }
}
