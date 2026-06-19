package com.example.tienda.repository;

import com.example.tienda.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {

    @Query("SELECT t FROM Tienda t")
    List<Tienda> findAll();

    @Query("SELECT t FROM Tienda t WHERE t.id_tienda = :id")
    List<Tienda> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Tienda t WHERE t.id_tienda = :id")
    void deleteTiendaById(@Param("id") Integer id);
}