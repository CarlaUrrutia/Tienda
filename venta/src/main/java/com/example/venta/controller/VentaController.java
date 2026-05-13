package com.example.venta.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.Service.TiendaService;
import com.example.gerente.Service.VentaService;
import com.example.gerente.model.Tarjeta;
import com.example.gerente.model.Tienda;
import com.example.gerente.model.Venta;

public class VentaController {
    @Autowired
    private VentaService  ventaServi;

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> v = ventaServi.getAllVenta();
        if (v.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(v);
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta ve){
        Venta ven = ventaServi.createVenta(ve);
        return ResponseEntity.status(HttpStatus.CREATED).body(ven);
    }

    @DeleteMapping("/{id_venta}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_venta){
        try{
            ventaServi.deleteVenta(id_venta);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_venta}")
    public ResponseEntity<Venta> buscarByid_venta(@PathVariable
        int id_venta){
            Venta vent = ventaServi.getPersoByid_venta(id_venta);
            if (vent==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(vent);
        }
}
