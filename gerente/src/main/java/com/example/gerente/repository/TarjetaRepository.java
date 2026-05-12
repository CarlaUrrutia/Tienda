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
public interface TarjetaRepository  extends JpaRepository<Tarjeta, Integer >{
    @Query ("SELECT tar FROM Tarjeta tar")
    List <Tarjeta> findAll(); /*Listar todos los empleados */

    @Query ("SELECT tar FROM Tarjeta tar WHERE tar.id_tarjeta =:id")
    List<Tarjeta> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Tarjeta tar WHERE tar.id_tarjeta =:id")
    void deleteTarjetaById( @Param("id")Integer id);/* Eliminar por el id*/

    //Tecnicamente no se puede modificar una tarjeta
    /*
    @Modifying
    @Transactional
    @Query ("UPDATE Proveedor pro SET pro.nombre =:nombre  ,pro.contacto =: contacto   WHERE pro.id_proveedor =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("contacto") String contacto
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}