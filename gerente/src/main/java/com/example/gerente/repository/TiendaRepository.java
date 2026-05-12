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
public interface TiendaRepository  extends JpaRepository<Tienda, Integer >{
    @Query ("SELECT tie FROM Tienda tie")
    List <Tienda> findAll(); /*Listar todos los empleados */

    @Query ("SELECT tie FROM Tienda tie WHERE tie.id_tienda =:id")
    List<Tienda> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Tienda tie WHERE tie.id_tienda =:id")
    void deleteTiendaById( @Param("id")Integer id);/* Eliminar por el id*/

    
    /
    @Modifying
    @Transactional
    @Query ("UPDATE Tienda tie SET tie.nombre_tienda =:nombre_tienda  ,tie.ubicacion =: ubicacion , tie.politicas =:politicas    WHERE tie.id_tienda =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre_tienda")String nombre_tienda,
                                @Param("ubicacion") String ubicacion,
                                @Param("politicas") String politicas,
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}