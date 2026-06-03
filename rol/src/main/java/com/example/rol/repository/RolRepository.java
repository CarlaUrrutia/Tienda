package com.example.rol.repository;

Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Tarjeta> findByid_rol(int id_rol);


    List<Rol> findBytipo(Long nombre_rol); 
}