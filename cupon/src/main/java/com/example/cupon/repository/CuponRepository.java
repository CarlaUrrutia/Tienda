package com.example.cupon.repository;

import com.example.cupon.model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Integer> {

    @Query("SELECT c FROM Cupon c")
    List<Cupon> findAll();

    @Query("SELECT c FROM Cupon c WHERE c.id_cupon = :id")
    List<Cupon> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cupon c WHERE c.id_cupon = :id")
    void deleteCuponById(@Param("id") Integer id);
}
