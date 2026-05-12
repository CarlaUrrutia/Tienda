package com.example.gerente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerente.model.Empleado;
import com.example.gerente.model.Factura;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    @Autowired
    private FacturaService  facturaServi;

    @GetMapping
    public ResponseEntity<List<Factura>> listar(){
        List<Factura> fac = facturaServi.getAllEmpleado();
        if (fac.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fac);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado emple){
        Empleado em = facturaServi.createDetalle(emple);
        return ResponseEntity.status(HttpStatus.CREATED).body(em);
    }

    @DeleteMapping("/{id_empleado}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_empleado){
        try{
            facturaServi.deleteEmpleado(id_empleado);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_empleado}")
    public ResponseEntity<Empleado> buscarByid_empleado(@PathVariable
        int id_empleado){
            Empleado emple = facturaServi.getPersoByid_empleado(id_empleado);
            if (emple==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(emple);
        }
}
