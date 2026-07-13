package com.example.inventario.repository;

import com.example.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Query("SELECT i FROM Inventario i")
    List<Inventario> findAll();

    @Query("SELECT i FROM Inventario i WHERE i.id = :id")
    List<Inventario> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Inventario i WHERE i.id = :id")
    void deleteInventarioById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Inventario i SET i.cantidad = :cantidad WHERE i.id = :id")
    int updateCantidad(@Param("id") Integer id, @Param("cantidad") int cantidad);
}
