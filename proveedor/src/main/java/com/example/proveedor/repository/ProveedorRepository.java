package com.example.proveedor.repository;

import com.example.proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query("SELECT p FROM Proveedor p")
    List<Proveedor> findAll();

    @Query("SELECT p FROM Proveedor p WHERE p.id_proveedor = :id")
    List<Proveedor> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Proveedor p WHERE p.id_proveedor = :id")
    void deleteProveedorById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Proveedor p SET p.nombre = :nombre, p.contacto = :contacto WHERE p.id_proveedor = :id")
    int updateProveedor(@Param("id") Integer id,
                        @Param("nombre") String nombre,
                        @Param("contacto") String contacto);
}