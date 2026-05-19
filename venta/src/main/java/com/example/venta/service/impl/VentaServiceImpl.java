package com.example.venta.service.impl;

import com.example.venta.model.Venta;
import com.example.venta.repository.VentaRepository;
import com.example.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Integer id) {
        List<Venta> lista = ventaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        Venta existente = getVentaById(id);
        if (existente != null) {
            existente.setFecha_venta(venta.getFecha_venta());
            existente.setId_cliente(venta.getId_cliente());
            existente.setId_empleado(venta.getId_empleado());
            return ventaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        ventaRepository.deleteVentaById(id);
    }
}
