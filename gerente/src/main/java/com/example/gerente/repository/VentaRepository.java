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
public interface VentaRepository  extends JpaRepository<Venta, Integer >{
    @Query ("SELECT v FROM Venta v")
    List <Venta> findAll(); /*Listar todos los empleados */

    @Query ("SELECT v FROM Venta v WHERE v.id_venta =:id")
    List<Venta> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Venta v WHERE v.id_venta =:id")
    void deleteVentaById( @Param("id")Integer id);/* Eliminar por el id*/

    
    /*
    @Modifying
    @Transactional
    @Query ("UPDATE Proveedor pro SET pro.nombre =:nombre  ,pro.contacto =: contacto   WHERE pro.id_proveedor =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("contacto") String contacto
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}