package com.example.factura.service;

import com.example.factura.DTO.FacturaDTO;
import java.util.List;

public interface FacturaService {
    List<FacturaDTO.Response> getAllFacturas();
    FacturaDTO.Response getFacturaById(Integer id);
    FacturaDTO.Response save(FacturaDTO.Request request);
    FacturaDTO.Response updateFactura(Integer id, FacturaDTO.Request request);
    void delete(Integer id);
}
