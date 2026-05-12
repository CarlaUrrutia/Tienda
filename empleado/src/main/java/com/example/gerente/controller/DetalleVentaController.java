

@RestController
@RequestMapping("/api/detalle")
public class DetalleVentaController {
    
    @Autowired
    private DetalleVentaService  detalleServi;

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar(){
        List<DetalleVenta> deta = detalleServi.getAllDetalle();
        if (deta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deta);
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalle){
        DetalleVenta detaven = detalleServi.createDetalle(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(detaven);
    }

    @DeleteMapping("/{id_detalle}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_detalle){
        try{
            detalleServi.deleteDetalle(id_detalle);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_detalle}")
    public ResponseEntity<DetalleVenta> buscarByid_detalle(@PathVariable
        int id_detalle){
            DetalleVenta detall = detalleServi.getPersoByid_detalle(id_detalle);
            if (detall==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(detall);
        }
    
        

}
