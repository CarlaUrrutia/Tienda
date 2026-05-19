package com.example.inventario.service.impl;

import com.example.inventario.model.Inventario;
import com.example.inventario.repository.InventarioRepository;
import com.example.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> getAllInventarios() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario getInventarioById(Integer id) {
        List<Inventario> lista = inventarioRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario updateInventario(Integer id, Inventario inventario) {
        Inventario existente = getInventarioById(id);
        if (existente != null) {
            existente.setCantidad(inventario.getCantidad());
            existente.setId_producto(inventario.getId_producto());
            existente.setId_tienda(inventario.getId_tienda());
            return inventarioRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        inventarioRepository.deleteInventarioById(id);
    }
}
