package com.example.gerente.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.gerente.model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    Optional<Tienda> findByid_tienda(int id_tienda);


    List<Tienda> findByubicacion(Long ubicacion); 
}
