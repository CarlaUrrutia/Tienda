package com.example.rol.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.gerente.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Tarjeta> findByid_rol(int id_rol);


    List<Rol> findBytipo(Long nombre_rol); 
}
