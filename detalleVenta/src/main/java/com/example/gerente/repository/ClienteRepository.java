package com.example.gerente.Repository;
import com.ejemplo.ms_persona.entity.Persona;
import com.example.gerente.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByid_cliente(int id_cliente);

    boolean existsByemail(String email);

    /*List<Persona> findByGeneroId(Long generoId); calmeishon*/
}