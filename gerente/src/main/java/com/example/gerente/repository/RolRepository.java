package com.example.gerente.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gerente.model.Empleado;

import jakarta.transaction.Transactional;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer >{
    @Query ("SELECT r FROM Rol r")
    List <Rol> findAll(); /*Listar todos los empleados */

    @Query ("SELECT r FROM Rol r WHERE r.id_rol =:id")
    List<Rol> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Rol r WHERE r.id_rol =:id")
    void deleteRolById( @Param("id")Integer id);/* Eliminar por el id*/

    
    
    @Modifying
    @Transactional
    @Query ("UPDATE Rol p SET r.nombre_rol =:nombre_rol   WHERE r.id_rol =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre_rol")String nombre_rol
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}