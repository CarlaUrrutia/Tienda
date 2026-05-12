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
public interface OfertaRepository  extends JpaRepository<Oferta, Integer >{
    @Query ("SELECT o FROM Oferta o")
    List <Oferta> findAll(); /*Listar todos los empleados */

    @Query ("SELECT o FROM Oferta o WHERE o.id_oferta =:id")
    List<Oferta> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Oferta o WHERE o.id_oferta =:id")
    void deleteOfertaById( @Param("id")Integer id);/* Eliminar por el id*/


    @Modifying
    @Transactional
    @Query ("UPDATE Oferta o SET o.descripcion =:descripcion , o.descuento =: descuento WHERE o.id_oferta =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("descripcion")String nombre,
                                @Param("descuento") int descuento,
                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
                    

    




  







}
