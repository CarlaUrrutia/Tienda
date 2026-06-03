import com.example.boleta.DTO.BoletaDTO;
import com.example.boleta.service.BoletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public List<BoletaDTO.Response> listar() {
        return boletaService.getAllBoletas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaDTO.Response> obtenerPorId(@PathVariable Integer id) {
        BoletaDTO.Response boleta = boletaService.getBoletaById(id);
        return boleta != null ? ResponseEntity.ok(boleta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BoletaDTO.Response> crear(@Valid @RequestBody BoletaDTO.Request request) {
        BoletaDTO.Response nueva = boletaService.save(request);
        return nueva != null ? ResponseEntity.ok(nueva) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boletaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
