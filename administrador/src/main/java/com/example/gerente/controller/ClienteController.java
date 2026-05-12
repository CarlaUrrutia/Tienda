

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService  clienteServi;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clie = clientedServi.getAllCliente();
        if (clie.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clie);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente clien){
        Cliente cli = clientedServi.createCliente(clien);
        return ResponseEntity.status(HttpStatus.CREATED).body(cli);
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Void> eliminar(@PathVariable int id_cliente){
        try{
            clientedServi.deleteCliente(id_cliente);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> buscarByid_cliente(@PathVariable
        int id_cliente){
            Cliente cli = clientedServi.getPersoByid_cliente(id_cliente);
            if (cli==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cli);
        }
    
        

}
*/