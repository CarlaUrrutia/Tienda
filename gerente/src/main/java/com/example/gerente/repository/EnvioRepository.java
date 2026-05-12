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
public interface EmpleadoRepository  extends JpaRepository<Empleado, Integer >{
    @Query ("SELECT en FROM Envio en")
    List <Empleado> findAll(); /*Listar todos los empleados */

    @Query ("SELECT en FROM Envio e WHERE en.id_envio =:id")
    List<Envio> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Envio en WHERE en.id_envio =:id")
    void deleteEnvioById( @Param("id")Integer id);/* Eliminar por el id*/


    @Modifying
    @Transactional
    @Query ("UPDATE Envio en SET en.estado =:estado, en.cantidad =: cantidad, en.direccion_destino =: direccion_destino WHERE e.id_empleado =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("estado")String estado,
                                @Param("cantidad") int cantidad,
                                @Param ("direccion_destino") String direccion_destino

                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
                    

    




  







}
