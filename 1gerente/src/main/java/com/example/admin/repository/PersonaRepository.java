package com.ejemplo.persona.repository;

import com.ejemplo.persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByRut(String rut);

    boolean existsByRut(String rut);

    List<Persona> findByGeneroId(Long generoId);
}
