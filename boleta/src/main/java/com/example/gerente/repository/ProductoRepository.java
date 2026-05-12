package com.example.gerente.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gerente.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByid_producto(int id_producto);


    List<Producto> findBynombre(Long nombre); 
}