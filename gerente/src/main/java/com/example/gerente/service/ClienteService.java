package com.example.gerente.service;
import java.util.List;
public interface ClienteService {
    List<ClienteDTO.Response> listarCLiente();
    ClienteDTO.Response buscarPorId (int id_cliente);
    ClienteDTO.Response crear(ClienteDTO.Request request);
    ClienteDTO.Response actualizar(int id_cliente, ClienteDTO.Request request);
    void eliminar(int id_cliente);

}