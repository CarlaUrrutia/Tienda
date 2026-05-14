

@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {
    
    @Autowired
    private CiudadService  ciudadServi;

    @GetMapping
    public ResponseEntity<List<Ciudad>> listar(){
        List<Ciudad> ciud = ciudadServi.getAllciudad();
        if (ciud.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ciud);
    }

    @PostMapping
    public ResponseEntity<Ciudad> guardar(@RequestBody Ciudad ciuda){
        Ciudad ciu = ciudadServi.createCiudad(ciuda);
        return ResponseEntity.status(HttpStatus.CREATED).body(ciu);
    }

    @DeleteMapping("/{id_ciudad}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_ciudad){
        try{
            ciudadServi.deleteCiudad(id_ciudad);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_ciudad}")
    public ResponseEntity<Ciudad> buscarByid_ciudad(@PathVariable
        int id_ciudad){
            Ciudad ci = ciudadServi.getPersoByid_ciudad(id_ciudad);
            if (ci==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ci);
        }
    
        

}
*/