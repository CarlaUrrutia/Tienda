package com.example.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.Repository.ClienteRepository;
import com.example.gerente.model.Cliente;

//import com.example.Repository.ClienteRepository;s
//import com.example.model.Cliente;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepo;

    public List<Cliente> getAllCliente(){
        return clienteRepo.findAll();
    }

    public Cliente getBoletayid_cliente(int id_cliente){
        List<Cliente> clie = clienteRepo.findByid_cliente(id_cliente);
        if (!clie.isEmpty()) {
            return clie.get(0);
        }
        return null;
    }

    
    public List<Cliente> getClienteRepo(String nombre){
        return clienteRepo.findByNombre(nombre);
    }
    

    public Cliente createCliente(Cliente cli){
        return clienteRepo.save(cli);
    }

    public void deleteCliente(int id_cliente){
        clienteRepo.deleteById(id_cliente);
    }
    
    
    public Cliente updateCliente(int id_cliente){
        Cliente cl= getClienteByid_cliente(id_cliente);
        if (client!=null) {
            cl.setNombre(cl.getNombre());
            cl.setApellido(cl.getApellido());
            cl.setEmail(cl.getEmail());
            return clienteRepo.save(client);   
        }
        return null;
    }
    
}
