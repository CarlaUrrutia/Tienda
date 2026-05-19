package com.example.venta.service;

import com.example.venta.model.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> getAllVentas();
    Venta getVentaById(Integer id);
    Venta save(Venta venta);
    Venta updateVenta(Integer id, Venta venta);
    void delete(Integer id);
}
