import com.example.empleado.model.Empleado;

import com.example.empleado.DTO.EmpleadoDTO;
import com.example.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired private EmpleadoService empleadoService;

    @GetMapping
    public List<EmpleadoDTO.Response> listar() { return empleadoService.getAllEmpleados(); }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EmpleadoDTO.Response r = empleadoService.getEmpleadoById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO.Response> crear(@Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoDTO.Request request) {
        EmpleadoDTO.Response r = empleadoService.updateEmpleado(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.ok().build();
    }
}

