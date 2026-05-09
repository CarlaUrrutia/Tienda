package com.example.gerente.controller;

public class GuiaPersonaController {
    
}

/*x|
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    
    @Autowired
    private PersonaService persoServi;

    @GetMapping
    public ResponseEntity<List<Persona>> listar(){
        List<Persona> perso = persoServi.getAllPersonas();
        if (perso.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perso);
    }

    @PostMapping
    public ResponseEntity<Persona> guardar(@RequestBody Persona perso){
        Persona npe = persoServi.createPerso(perso);
        return ResponseEntity.status(HttpStatus.CREATED).body(npe);
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> eliminar(@PathVariable String run){
        try{
            persoServi.deletePerso(run);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Persona> buscarByRut(@PathVariable
        String run){
            Persona pe = persoServi.getPersoByRut(run);
            if (pe==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pe);
        }
    
        

}
*/