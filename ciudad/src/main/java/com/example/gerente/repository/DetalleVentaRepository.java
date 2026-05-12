package com.example.gerente.Repository;
import com.ejemplo.ms_persona.entity.Persona;
import com.example.gerente.model.DetalleVenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    Optional<DetalleVenta> findByid_detalle(int id_detalle);

    boolean existsBycantidad(int cantidad);
}