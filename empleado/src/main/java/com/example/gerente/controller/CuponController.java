

@RestController
@RequestMapping("/api/cupon")
public class CuponController {
    
    @Autowired
    private CuponService  cuponServi;

    @GetMapping
    public ResponseEntity<List<Cupon>> listar(){
        List<Cupon> cup = cuponServi.getAllCupon();
        if (cup.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cup);
    }

    @PostMapping
    public ResponseEntity<Cupon> guardar(@RequestBody Cupon cupo){
        Cupon cu = cuponServi.createCupon(cupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(cu);
    }

    @DeleteMapping("/{id_cupon}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_cupon){
        try{
            cuponServi.deleteCupon(id_cupon);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_cupon}")
    public ResponseEntity<Cupon> buscarByid_cupon(@PathVariable
        int id_cupon){
            Cupon c = cuponServi.getPersoByid_cupon(id_cupon);
            if (c==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(c);
        }
    
        

}
*/