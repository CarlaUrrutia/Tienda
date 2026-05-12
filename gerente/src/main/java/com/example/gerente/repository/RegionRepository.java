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
public interface RegionRepository  extends JpaRepository<Region, Integer >{
    @Query ("SELECT re FROM Region re")
    List <Region> findAll(); /*Listar todos los empleados */

    @Query ("SELECT re FROM Region re WHERE re.id_region =:id")
    List<Region> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Region re WHERE re.id_region =:id")
    void deleteRegionById( @Param("id")Integer id);/* Eliminar por el id*/

    
    /*
    @Modifying
    @Transactional
    @Query ("UPDATE Proveedor pro SET pro.nombre =:nombre  ,pro.contacto =: contacto   WHERE pro.id_proveedor =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("contacto") String contacto
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}