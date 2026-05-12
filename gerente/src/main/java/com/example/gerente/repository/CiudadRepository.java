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
public interface CiudadRepository  extends JpaRepository<Ciudad, Integer >{
    @Query ("SELECT ci FROM Ciudad ci")
    List <Ciudad> findAll(); /*Listar todos los empleados */

    @Query ("SELECT ci FROM Ciudad ci WHERE ci.id_ciudad =:id")
    List<Ciudad> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Ciudad ci WHERE ci.id_ciudad =:id")
    void deleteCiudadById( @Param("id")Integer id);/* Eliminar por el id*/

    //duda al profe, se modifica realmente una ciudad
    /*
    @Modifying
    @Transactional
    @Query ("UPDATE Empleado e SET e.nombre =:nombre,e.apellido =: apellido,e.sueldo =: sueldo,e.rol =:rol WHERE e.id_empleado =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("apellido") String apellido,
                                @Param ("sueldo") float sueldo,
                                @Param ("rol") String rol
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
                    

    




  







}
