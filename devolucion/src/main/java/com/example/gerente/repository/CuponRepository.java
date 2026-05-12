package com.example.gerente.Repository;
import com.ejemplo.ms_persona.entity.Persona;
import com.example.gerente.model.Cupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {

    Optional<Cupon> findByid_cupon(int id_cupon);

    boolean existsBycodigo(int codigo);
}