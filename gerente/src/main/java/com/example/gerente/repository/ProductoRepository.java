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
public interface ProductoRepository  extends JpaRepository<Producto, Integer >{
    @Query ("SELECT pr FROM Producto pr")
    List <Producto> findAll(); /*Listar todos los empleados */

    @Query ("SELECT pr FROM Producto pr WHERE pr.id_producto =:id")
    List<Producto> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Producto pr WHERE pr.id_producto =:id")
    void deleteDetalleById( @Param("id")Integer id);/* Eliminar por el id*/

    //
    
    @Modifying
    @Transactional
    @Query ("UPDATE Producto pr SET pr.nombre =:nombre  ,pr.apellido =: apellido  ,pr.sueldo =: sueldo  WHERE pr.id_producto =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("nombre")String nombre,
                                @Param("precio_venta") int precio_venta,

                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}