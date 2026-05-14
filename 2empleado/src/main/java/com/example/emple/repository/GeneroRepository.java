package com.ejemplo.genero.repository;

import com.ejemplo.genero.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByDescripcionIgnoreCase(String descripcion);

    boolean existsByDescripcionIgnoreCase(String descripcion);
}
