package com.example.inventario.repository;

import com.ejemplo.ms_persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Query("SELECT i FROM Inventario i")
    List<Inventario> findAll();

    @Query("SELECT i FROM Inventario i WHERE i.id_inventario = :id")
    List<Inventario> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Inventario i WHERE i.id_inventario = :id")
    void deleteInventarioById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Inventario i SET i.cantidad = :cantidad WHERE i.id_inventario = :id")
    int updateCantidad(@Param("id") Integer id, @Param("cantidad") int cantidad);
}