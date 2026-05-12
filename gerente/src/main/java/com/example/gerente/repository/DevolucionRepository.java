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
public interface DevolucionRepository  extends JpaRepository<Devolucion, Integer >{
    @Query ("SELECT de FROM Devolucion de")
    List <Devolucion> findAll(); /*Listar todos los empleados */

    @Query ("SELECT de FROM Devolucion dv WHERE de.id_devolucion =:id")
    List<Devolucion> buscarPorId(Integer id);/* Listar por id de empleado*/

    @Modifying
    @Transactional
    @Query("DELETE FROM Devolucion de WHERE de.id_devolucion =:id")
    void deleteDevolucionById( @Param("id")Integer id);/* Eliminar por el id*/

    //
    @Modifying
    @Transactional
    @Query ("UPDATE Devolucion de SET de.motivo =:motivo,de.monto_reembolso =: monto_reembolso,ed.cantidad_devuelta =: cantidad_devuelta WHERE de.id_devolucion =:id  ")
             int updateEmpleado(@Param("id")Integer id,
                                @Param ("motivo")String motivo,
                                @Param("monto_reembolso") int monto_reembolso,
                                @Param ("cantidad_devuelta") int cantidad_devuelta

                                                         ); /* hacerlo con todos los datps que se quieran modificar, siguiendo la misma estructura:)*/
}