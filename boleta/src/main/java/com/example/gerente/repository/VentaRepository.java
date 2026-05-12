package com.example.gerente.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




import com.example.gerente.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByid_venta(int venta);

 
}
