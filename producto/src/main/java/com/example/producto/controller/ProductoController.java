package com.example.producto.controller;

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
import com.example.gerente.model.Producto;

public class ProductoController {
    @Autowired
    private ProductoService  productoServi;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> prod = productoServi.getAllProducto();
        if (prod.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prod);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto pro){
        Producto p = productoServi.createProducto(pro);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_producto){
        try{
            productoServi.deleteOferta(id_producto);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<Producto> buscarByid_producto(@PathVariable
        int id_producto){
            Producto prodc = productoServi.getPersoByid_producto(id_producto);
            if (prodc==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(prodc);
        }
}
