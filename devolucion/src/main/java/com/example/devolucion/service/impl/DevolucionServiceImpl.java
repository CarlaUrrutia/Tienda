package com.example.devolucion.service.impl;

import com.example.devolucion.DTO.DevolucionDTO;
import com.example.devolucion.client.*;
import com.example.devolucion.model.Devolucion;
import com.example.devolucion.repository.DevolucionRepository;
import com.example.devolucion.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    @Autowired private DevolucionRepository devolucionRepository;
    @Autowired private EmpleadoClient empleadoClient;
    @Autowired private ClienteClient clienteClient;
    @Autowired private TarjetaClient tarjetaClient;
    @Autowired private VentaClient ventaClient;
    @Autowired private ProductoClient productoClient;

    private DevolucionDTO.Response toResponse(Devolucion d) {
        return new DevolucionDTO.Response(
            d.getId_devolucion(), d.getFecha_devolucion(), d.getMotivo(),
            d.getMonto_reembolso(), d.getCantidad_devuelta(),
            empleadoClient.getEmpleadoById(d.getId_empleado()),
            clienteClient.getClienteById(d.getId_cliente()),
            tarjetaClient.getTarjetaById(d.getId_tarjeta()),
            ventaClient.getVentaById(d.getId_venta()),
            productoClient.getProductoById(d.getId_producto())
        );
    }

    @Override
    public List<DevolucionDTO.Response> getAllDevoluciones() {
        return devolucionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public DevolucionDTO.Response getDevolucionById(Integer id) {
        List<Devolucion> lista = devolucionRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public DevolucionDTO.Response save(DevolucionDTO.Request request) {
        Devolucion d = new Devolucion();
        d.setMotivo(request.getMotivo());
        d.setMonto_reembolso(request.getMonto_reembolso());
        d.setCantidad_devuelta(request.getCantidad_devuelta());
        d.setId_empleado(request.getId_empleado());
        d.setId_cliente(request.getId_cliente());
        d.setId_tarjeta(request.getId_tarjeta());
        d.setId_venta(request.getId_venta());
        d.setId_producto(request.getId_producto());
        return toResponse(devolucionRepository.save(d));
    }

    @Override
    public DevolucionDTO.Response updateDevolucion(Integer id, DevolucionDTO.Request request) {
        List<Devolucion> lista = devolucionRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Devolucion d = lista.get(0);
        d.setMotivo(request.getMotivo());
        d.setMonto_reembolso(request.getMonto_reembolso());
        d.setCantidad_devuelta(request.getCantidad_devuelta());
        return toResponse(devolucionRepository.save(d));
    }

    @Override
    public void delete(Integer id) { devolucionRepository.deleteDevolucionById(id); }
}
