package com.example.factura.service;

import com.example.factura.model.Factura;
import java.util.List;

public interface FacturaService {
    List<Factura> getAllFacturas();
    Factura getFacturaById(Integer id);
    Factura save(Factura factura);
    Factura updateFactura(Integer id, Factura factura);
    void delete(Integer id);
}
