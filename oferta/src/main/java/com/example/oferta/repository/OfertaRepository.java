package com.example.oferta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.oferta.model.Oferta;

import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

    @Query("SELECT o FROM Oferta o")
    List<Oferta> findAll();

    @Query("SELECT o FROM Oferta o WHERE o.id = :id")
    List<Oferta> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Oferta o WHERE o.id = :id")
    void deleteOfertaById(@Param("id") Integer id);
}