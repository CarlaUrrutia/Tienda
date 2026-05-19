package com.example.detalleVenta.service.impl;

import com.example.detalleVenta.model.DetalleVenta;
import com.example.detalleVenta.repository.DetalleVentaRepository;
import com.example.detalleVenta.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> getAllDetalles() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta getDetalleById(Integer id) {
        List<DetalleVenta> lista = detalleVentaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public List<DetalleVenta> getDetallesByVenta(Integer idVenta) {
        return detalleVentaRepository.buscarPorVenta(idVenta);
    }

    @Override
    public DetalleVenta save(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public DetalleVenta updateDetalle(Integer id, DetalleVenta detalle) {
        DetalleVenta existente = getDetalleById(id);
        if (existente != null) {
            existente.setCantidad(detalle.getCantidad());
            existente.setPrecio_unitario_venta(detalle.getPrecio_unitario_venta());
            existente.setId_venta(detalle.getId_venta());
            existente.setId_producto(detalle.getId_producto());
            return detalleVentaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        detalleVentaRepository.deleteDetalleById(id);
    }
}
