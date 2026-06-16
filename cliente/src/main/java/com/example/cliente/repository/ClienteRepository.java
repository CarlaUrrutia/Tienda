package com.example.cliente.repository;


import com.example.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c")
    List<Cliente> findAll();

    @Query("SELECT c FROM Cliente c WHERE c.id_cliente = :id")
    List<Cliente> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente c WHERE c.id_cliente = :id")
    void deleteClienteById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.nombre = :nombre, c.apellido = :apellido, c.email = :email WHERE c.id_cliente = :id")
    int updateCliente(@Param("id") Integer id,
                      @Param("nombre") String nombre,
                      @Param("apellido") String apellido,
                      @Param("email") String email);
}
