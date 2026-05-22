package com.example.detalleVenta.service.impl;

import com.example.detalleVenta.DTO.DetalleVentaDTO;
import com.example.detalleVenta.client.ProductoClient;
import com.example.detalleVenta.client.VentaClient;
import com.example.detalleVenta.model.DetalleVenta;
import com.example.detalleVenta.repository.DetalleVentaRepository;
import com.example.detalleVenta.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired private DetalleVentaRepository detalleVentaRepository;
    @Autowired private VentaClient ventaClient;
    @Autowired private ProductoClient productoClient;

    private DetalleVentaDTO.Response toResponse(DetalleVenta d) {
        return new DetalleVentaDTO.Response(
            d.getId_detalle(), d.getCantidad(), d.getPrecio_unitario_venta(),
            ventaClient.getVentaById(d.getId_venta()),
            productoClient.getProductoById(d.getId_producto())
        );
    }

    @Override
    public List<DetalleVentaDTO.Response> getAllDetalles() {
        return detalleVentaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public DetalleVentaDTO.Response getDetalleById(Integer id) {
        List<DetalleVenta> lista = detalleVentaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public List<DetalleVentaDTO.Response> getDetallesByVenta(Integer idVenta) {
        return detalleVentaRepository.buscarPorVenta(idVenta).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public DetalleVentaDTO.Response save(DetalleVentaDTO.Request request) {
        DetalleVenta d = new DetalleVenta();
        d.setCantidad(request.getCantidad());
        d.setPrecio_unitario_venta(request.getPrecio_unitario_venta());
        d.setId_venta(request.getId_venta());
        d.setId_producto(request.getId_producto());
        return toResponse(detalleVentaRepository.save(d));
    }

    @Override
    public DetalleVentaDTO.Response updateDetalle(Integer id, DetalleVentaDTO.Request request) {
        List<DetalleVenta> lista = detalleVentaRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        DetalleVenta d = lista.get(0);
        d.setCantidad(request.getCantidad());
        d.setPrecio_unitario_venta(request.getPrecio_unitario_venta());
        d.setId_venta(request.getId_venta());
        d.setId_producto(request.getId_producto());
        return toResponse(detalleVentaRepository.save(d));
    }

    @Override
    public void delete(Integer id) { detalleVentaRepository.deleteDetalleById(id); }
}
