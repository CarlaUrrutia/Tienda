

import com.example.devolucion.DTO.DevolucionDTO;
import com.example.devolucion.service.DevolucionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public List<DevolucionDTO.Response> listar() {
        return devolucionService.getAllDevoluciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> obtenerPorId(@PathVariable Integer id) {
        DevolucionDTO.Response r = devolucionService.getDevolucionById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DevolucionDTO.Response> crear(@Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevolucionDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody DevolucionDTO.Request request) {
        DevolucionDTO.Response r = devolucionService.updateDevolucion(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        devolucionService.delete(id);
        return ResponseEntity.ok().build();
    }
}


