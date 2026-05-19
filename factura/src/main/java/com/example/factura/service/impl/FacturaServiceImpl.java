package com.example.factura.service.impl;

import com.example.factura.model.Factura;
import com.example.factura.repository.FacturaRepository;
import com.example.factura.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura getFacturaById(Integer id) {
        List<Factura> lista = facturaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura updateFactura(Integer id, Factura factura) {
        Factura existente = getFacturaById(id);
        if (existente != null) {
            existente.setFecha(factura.getFecha());
            existente.setTotal(factura.getTotal());
            existente.setId_venta(factura.getId_venta());
            existente.setId_cliente(factura.getId_cliente());
            return facturaRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        facturaRepository.deleteFacturaById(id);
    }
}
