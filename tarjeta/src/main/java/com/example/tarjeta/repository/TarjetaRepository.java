package com.example.tarjeta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.gerente.model.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {

    @Query("SELECT t FROM Tarjeta t")
    List<Tarjeta> findAll();

    @Query("SELECT t FROM Tarjeta t WHERE t.id_tarjeta = :id")
    List<Tarjeta> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Tarjeta t WHERE t.id_tarjeta = :id")
    void deleteTarjetaById(@Param("id") Integer id);
}
