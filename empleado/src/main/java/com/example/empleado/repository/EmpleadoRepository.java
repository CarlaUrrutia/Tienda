//todos los package me dan error
package com.example.empleado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.empleado.model.Empleado;

import jakarta.transaction.Transactional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query("SELECT e FROM Empleado e")
    List<Empleado> findAll();

    @Query("SELECT e FROM Empleado e WHERE e.id_empleado = :id")
    List<Empleado> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Empleado e WHERE e.id_empleado = :id")
    void deleteEmpleadoById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Empleado e SET e.nombre = :nombre, e.apellido = :apellido, e.sueldo = :sueldo WHERE e.id_empleado = :id")
    int updateEmpleado(@Param("id") Integer id,
                       @Param("nombre") String nombre,
                       @Param("apellido") String apellido,
                       @Param("sueldo") float sueldo);
}
