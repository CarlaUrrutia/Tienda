package com.example.factura.service.impl;

import com.example.factura.DTO.FacturaDTO;
import com.example.factura.client.ClienteClient;
import com.example.factura.client.VentaClient;
import com.example.factura.model.Factura;
import com.example.factura.repository.FacturaRepository;
import com.example.factura.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired private FacturaRepository facturaRepository;
    @Autowired private ClienteClient clienteClient;
    @Autowired private VentaClient ventaClient;

    private FacturaDTO.Response toResponse(Factura f) {
        return new FacturaDTO.Response(
            f.getId_factura(), f.getFecha(), f.getTotal(),
            clienteClient.getClienteById(f.getId_cliente()),
            ventaClient.getVentaById(f.getId_venta())
        );
    }

    @Override
    public List<FacturaDTO.Response> getAllFacturas() {
        return facturaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public FacturaDTO.Response getFacturaById(Integer id) {
        List<Factura> lista = facturaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public FacturaDTO.Response save(FacturaDTO.Request request) {
        Factura f = new Factura();
        f.setFecha(request.getFecha());
        f.setTotal(request.getTotal());
        f.setId_venta(request.getId_venta());
        f.setId_cliente(request.getId_cliente());
        return toResponse(facturaRepository.save(f));
    }

    @Override
    public FacturaDTO.Response updateFactura(Integer id, FacturaDTO.Request request) {
        List<Factura> lista = facturaRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Factura f = lista.get(0);
        f.setFecha(request.getFecha());
        f.setTotal(request.getTotal());
        f.setId_venta(request.getId_venta());
        f.setId_cliente(request.getId_cliente());
        return toResponse(facturaRepository.save(f));
    }

    @Override
    public void delete(Integer id) { facturaRepository.deleteFacturaById(id); }
}
