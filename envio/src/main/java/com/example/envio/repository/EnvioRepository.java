package com.example.envio.repository;

import com.example.envio.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    @Query("SELECT e FROM Envio e")
    List<Envio> findAll();

    @Query("SELECT e FROM Envio e WHERE e.id_envio = :id")
    List<Envio> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Envio e SET e.estado = :estado WHERE e.id_envio = :id")
    int updateEstado(@Param("id") Integer id, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query("DELETE FROM Envio e WHERE e.id_envio = :id")
    void deleteEnvioById(@Param("id") Integer id);
}
