package com.example.gerente.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gerente.model.Oferta;

    @Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    Optional<Oferta> findByid_oferta(int id_oferta);

    boolean existsBydescripcion(String descripcion);
}
