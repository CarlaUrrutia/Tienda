package com.example.boleta.controller;

/*
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
*/
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {
    
    @Autowired
    private BoletaService boletaServi;

    @GetMapping
    public ResponseEntity<List<Boleta>> listar(){
        List<Boleta> bolet = boletaServi.getAllBoleta();
        if (bolet.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bolet);
    }

    @PostMapping
    public ResponseEntity<Boleta> guardar(@RequestBody Boleta bole){
        Boleta npe = boletaServi.createBoleta(bole);
        return ResponseEntity.status(HttpStatus.CREATED).body(npe);
    }

    @DeleteMapping("/{id_boleta}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_boleta){
        try{
            persoServi.deleteBoleta(id_boleta);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_boleta}")
    public ResponseEntity<Boleta> buscarByid_boleta(@PathVariable
        int id_boleta){
            Boleta bo = boletaServi.getPersoByid_boleta(id_boleta);
            if (bo==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(bo);
        }
    
        

}
*/