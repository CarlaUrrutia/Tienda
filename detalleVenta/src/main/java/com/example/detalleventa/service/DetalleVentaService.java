package com.example.detalleVenta.service;

import com.example.detalleVenta.DTO.DetalleVentaDTO;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVentaDTO.Response> getAllDetalles();
    DetalleVentaDTO.Response getDetalleById(Integer id);
    List<DetalleVentaDTO.Response> getDetallesByVenta(Integer idVenta);
    DetalleVentaDTO.Response save(DetalleVentaDTO.Request request);
    DetalleVentaDTO.Response updateDetalle(Integer id, DetalleVentaDTO.Request request);
    void delete(Integer id);
}
