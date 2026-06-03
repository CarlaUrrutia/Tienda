package com.example.boleta.repository;


import com.example.boleta.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

    @Query("SELECT b FROM Boleta b")
    List<Boleta> findAll();

    @Query("SELECT b FROM Boleta b WHERE b.id_boleta = :id")
    List<Boleta> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Boleta b WHERE b.id_boleta = :id")
    void deleteBoletaById(@Param("id") Integer id);
}
