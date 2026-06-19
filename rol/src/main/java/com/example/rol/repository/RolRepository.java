package com.example.rol.repository;

import com.example.rol.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("SELECT r FROM Rol r")
    List<Rol> findAll();

    @Query("SELECT r FROM Rol r WHERE r.id_rol = :id")
    List<Rol> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Rol r WHERE r.id_rol = :id")
    void deleteRolById(@Param("id") Integer id);
}