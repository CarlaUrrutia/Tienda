package com.example.venta.repository;

import com.example.venta.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT v FROM Venta v")
    List<Venta> findAll();

    @Query("SELECT v FROM Venta v WHERE v.id_venta = :id")
    List<Venta> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Venta v WHERE v.id_venta = :id")
    void deleteVentaById(@Param("id") Integer id);
}