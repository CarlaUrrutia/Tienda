package com.example.factura.repository;


import com.example.factura.model.Factura;

import feign.Param;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query("SELECT f FROM Factura f")
    List<Factura> findAll();

    @Query("SELECT f FROM Factura f WHERE f.id_factura = :id")
    List<Factura> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Factura f WHERE f.id_factura = :id")
    void deleteFacturaById(@Param("id") Integer id);
}