package com.example.venta.service.impl;

import com.example.venta.dto.VentaDTO;
import com.example.venta.client.ClienteClient;
import com.example.venta.client.EmpleadoClient;
import com.example.venta.dto.ClienteResponse;
import com.example.venta.dto.EmpleadoResponse;
import com.example.venta.model.Venta;
import com.example.venta.repository.VentaRepository;
import com.example.venta.service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteClient clienteClient;
    @Autowired private EmpleadoClient empleadoClient;

    private VentaDTO.Response toResponse(Venta venta) {
        ClienteResponse cliente = clienteClient.getClienteById(venta.getId_cliente());
        EmpleadoResponse empleado = empleadoClient.getEmpleadoById(venta.getId_empleado());
        return new VentaDTO.Response(
                venta.getId_venta(),
                venta.getFecha_venta(),
                cliente,
                empleado
        );
    }

    @Override
    public List<VentaDTO.Response> getAllVentas() {
        return ventaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public VentaDTO.Response getVentaById(Integer id) {
        List<Venta> lista = ventaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Venta con id " + id + " no encontrada");
        }
        return toResponse(lista.get(0));
    }

    @Override
    public VentaDTO.Response save(VentaDTO.Request request) {
        Venta venta = new Venta();
        venta.setFecha_venta(request.getFecha_venta());
        venta.setId_cliente(request.getId_cliente());
        venta.setId_empleado(request.getId_empleado());
        return toResponse(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO.Response updateVenta(Integer id, VentaDTO.Request request) {
        List<Venta> lista = ventaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Venta con id " + id + " no encontrada para actualizar");
        }
        Venta existente = lista.get(0);
        existente.setFecha_venta(request.getFecha_venta());
        existente.setId_cliente(request.getId_cliente());
        existente.setId_empleado(request.getId_empleado());
        return toResponse(ventaRepository.save(existente));
    }

    @Override
    public void delete(Integer id) {
        getVentaById(id); // valida existencia antes de borrar
        ventaRepository.deleteVentaById(id);
    }
}