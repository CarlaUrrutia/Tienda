import com.example.empleado.model.Empleado;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService  empleadoServi;

    @GetMapping
    public ResponseEntity<List<Empleado>> listar(){
        List<Empleado> emp = empleadoServi.getAllEmpleado();
        if (emp.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(emp);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado emple){
        Empleado em = empleadoServi.createDetalle(emple);
        return ResponseEntity.status(HttpStatus.CREATED).body(em);
    }

    @DeleteMapping("/{id_empleado}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_empleado){
        try{
            empleadoServi.deleteEmpleado(id_empleado);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_empleado}")
    public ResponseEntity<Empleado> buscarByid_empleado(@PathVariable
        int id_empleado){
            Empleado emple = empleadoServi.getPersoByid_empleado(id_empleado);
            if (emple==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(emple);
        }
    
        

}
