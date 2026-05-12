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
public interface InventarioRepository  extends JpaRepository<Inventario, Integer >{
    @Query ("SELECT i FROM Inventario i")
    List <Inventario> findAll(); /*Listar todos los empleados */

    @Query ("SELECT i FROM Inventario i WHERE i.id_inventario =:id")
    List<Inventario> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Inventario i WHERE e.id_inventario =:id")
    void deleteInventarioById( @Param("id")Integer id);/* Eliminar por el id*/


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
