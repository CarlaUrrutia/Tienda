package com.example.detalleventa.repository;

import com.example.detalleventa.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    @Query("SELECT d FROM DetalleVenta d")
    List<DetalleVenta> findAll();

    @Query("SELECT d FROM DetalleVenta d WHERE d.id_detalle = :id")
    List<DetalleVenta> buscarPorId(@Param("id") Integer id);

    @Query("SELECT d FROM DetalleVenta d WHERE d.id_venta = :idVenta")
    List<DetalleVenta> buscarPorVenta(@Param("idVenta") Integer idVenta);

    @Modifying
    @Transactional
    @Query("DELETE FROM DetalleVenta d WHERE d.id_detalle = :id")
    void deleteDetalleById(@Param("id") Integer id);
}
