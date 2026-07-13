package com.example.tienda.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.gerente.model.Tienda;

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
