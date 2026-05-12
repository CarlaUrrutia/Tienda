package com.example.gerente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.model.Oferta;
import com.example.gerente.model.Producto;
import com.example.gerente.model.Proveedor;

public class ProveedorController {
    @Autowired
    private ProveedorService  proveedorServi;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar(){
        List<Proveedor> prov = proveedorServi.getAllProveedor();
        if (prov.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prov);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor pro){
        Proveedor pr = proveedorServi.createProveedor(pro);
        return ResponseEntity.status(HttpStatus.CREATED).body(pr);
    }

    @DeleteMapping("/{id_proveedor}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_proveedor){
        try{
            proveedorServi.deleteProveedor(id_proveedor);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_proveedor}")
    public ResponseEntity<Producto> buscarByid_proveedor(@PathVariable
        int id_proveedor){
            Proveedor prove = proveedorServi.getPersoByid_proveedor(id_proveedor);
            if (prove==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(prove);
        }
}
