package com.example.detalleVenta.service;

import com.example.detalleVenta.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> getAllDetalles();
    DetalleVenta getDetalleById(Integer id);
    List<DetalleVenta> getDetallesByVenta(Integer idVenta);
    DetalleVenta save(DetalleVenta detalle);
    DetalleVenta updateDetalle(Integer id, DetalleVenta detalle);
    void delete(Integer id);
}
