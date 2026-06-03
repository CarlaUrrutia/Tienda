import com.example.devolucion.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {

    @Query("SELECT d FROM Devolucion d")
    List<Devolucion> findAll();

    @Query("SELECT d FROM Devolucion d WHERE d.id_devolucion = :id")
    List<Devolucion> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Devolucion d WHERE d.id_devolucion = :id")
    void deleteDevolucionById(@Param("id") Integer id);
}
