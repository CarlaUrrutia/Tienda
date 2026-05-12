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
public interface ClienteRepository  extends JpaRepository<Cliente, Integer >{
    @Query ("SELECT cli FROM Cliente cli")
    List <Cliente> findAll(); /*Listar todos los empleados */

    @Query ("SELECT cli FROM CLiente cli WHERE cli.id_cliente =:id")
    List<Cliente> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente cli WHERE cli.id_cliente =:id")
    void deleteClienteById( @Param("id")Integer id);/* Eliminar por el id*/


    @Modifying
    @Transactional
    @Query ("UPDATE Cliente cli SET cli.nombre =:nombre,cli.apellido =: apellido,cli.email =: email WHERE cli.id_empleado =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("apellido") String apellido,
                                @Param ("email") String email
                                
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
                    

    




  







}
