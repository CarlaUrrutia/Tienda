package com.example.envio.service.impl;

import com.example.envio.DTO.EnvioDTO;
import com.example.envio.client.ClienteClient;
import com.example.envio.client.EmpleadoClient;
import com.example.envio.client.VentaClient;
import com.example.envio.model.Envio;
import com.example.envio.repository.EnvioRepository;
import com.example.envio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired private EnvioRepository envioRepository;
    @Autowired private VentaClient ventaClient;
    @Autowired private ClienteClient clienteClient;
    @Autowired private EmpleadoClient empleadoClient;

    private EnvioDTO.Response toResponse(Envio e) {
        return new EnvioDTO.Response(
            e.getId_envio(), e.getFecha_envio(), e.getFecha_estimada_entrega(),
            e.getEstado(), e.getDireccion_destino(),
            ventaClient.getVentaById(e.getId_venta()),
            clienteClient.getClienteById(e.getId_cliente()),
            empleadoClient.getEmpleadoById(e.getId_empleado()),
            e.getId_ciudad()
        );
    }

    @Override
    public List<EnvioDTO.Response> getAllEnvios() {
        return envioRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public EnvioDTO.Response getEnvioById(Integer id) {
        List<Envio> lista = envioRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public EnvioDTO.Response save(EnvioDTO.Request request) {
        Envio e = new Envio();
        e.setFecha_envio(request.getFecha_envio());
        e.setFecha_estimada_entrega(request.getFecha_estimada_entrega());
        e.setEstado(request.getEstado());
        e.setDireccion_destino(request.getDireccion_destino());
        e.setId_venta(request.getId_venta());
        e.setId_cliente(request.getId_cliente());
        e.setId_empleado(request.getId_empleado());
        e.setId_ciudad(request.getId_ciudad());
        return toResponse(envioRepository.save(e));
    }

    @Override
    public EnvioDTO.Response updateEnvio(Integer id, EnvioDTO.Request request) {
        List<Envio> lista = envioRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Envio e = lista.get(0);
        e.setEstado(request.getEstado());
        e.setDireccion_destino(request.getDireccion_destino());
        return toResponse(envioRepository.save(e));
    }

    @Override
    public void delete(Integer id) { envioRepository.deleteEnvioById(id); }
}
