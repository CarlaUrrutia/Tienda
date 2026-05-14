package com.example.ciudad.repository;

//import com.ejemplo.ms_persona.entity.Persona;
import com.example.ciudad.model.Ciudad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    Optional<Ciudad> findByid_ciudad(int ciudad);

    List<Ciudad> findBynombre(String nombre);
}