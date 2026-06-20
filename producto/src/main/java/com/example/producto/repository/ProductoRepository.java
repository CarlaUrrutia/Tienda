package com.example.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.producto.model.Producto;

import jakarta.transaction.Transactional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p")
    List<Producto> findAll();

    @Query("SELECT p FROM Producto p WHERE p.id_producto = :id")
    List<Producto> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Producto p WHERE p.id_producto = :id")
    void deleteProductoById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.nombre = :nombre, p.precio_venta = :precio WHERE p.id_producto = :id")
    int updateProducto(@Param("id") Integer id,
                       @Param("nombre") String nombre,
                       @Param("precio") int precio_venta);
}