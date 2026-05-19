package com.example.devolucion.service.impl;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.repository.DevolucionRepository;
import com.example.devolucion.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Override
    public List<Devolucion> getAllDevoluciones() {
        return devolucionRepository.findAll();
    }

    @Override
    public Devolucion getDevolucionById(Integer id) {
        List<Devolucion> lista = devolucionRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Devolucion save(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    @Override
    public Devolucion updateDevolucion(Integer id, Devolucion devolucion) {
        Devolucion existente = getDevolucionById(id);
        if (existente != null) {
            existente.setMotivo(devolucion.getMotivo());
            existente.setMonto_reembolso(devolucion.getMonto_reembolso());
            existente.setCantidad_devuelta(devolucion.getCantidad_devuelta());
            return devolucionRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        devolucionRepository.deleteDevolucionById(id);
    }
}
