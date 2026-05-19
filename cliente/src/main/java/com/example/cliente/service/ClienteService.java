package com.example.cliente.service;

import com.example.cliente.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Integer id);
    Cliente save(Cliente cliente);
    Cliente updateCliente(Integer id, Cliente cliente);
    void delete(Integer id);
}
