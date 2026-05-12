package com.example.gerente.Repository;
import com.ejemplo.ms_persona.entity.Persona;
import com.example.gerente.model.Boleta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, long> {

    Optional<Boleta> findByid_boleta(int id_boleta);
}
