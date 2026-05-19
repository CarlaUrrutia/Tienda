package com.example.cliente.service.impl;

import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepository;
import com.example.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        List<Cliente> lista = clienteRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) {
        Cliente existente = getClienteById(id);
        if (existente != null) {
            existente.setNombre(cliente.getNombre());
            existente.setApellido(cliente.getApellido());
            existente.setEmail(cliente.getEmail());
            return clienteRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteClienteById(id);
    }
}
