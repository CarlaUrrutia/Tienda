package com.example.venta.service;

import com.example.venta.DTO.VentaDTO;
import java.util.List;

public interface VentaService {
    List<VentaDTO.Response> getAllVentas();
    VentaDTO.Response getVentaById(Integer id);
    VentaDTO.Response save(VentaDTO.Request request);
    VentaDTO.Response updateVenta(Integer id, VentaDTO.Request request);
    void delete(Integer id);
}
