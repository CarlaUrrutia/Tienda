package com.example.proveedor.service.impl;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepository;
import com.example.proveedor.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id) {
        List<Proveedor> lista = proveedorRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        Proveedor existente = getProveedorById(id);
        if (existente != null) {
            existente.setNombre(proveedor.getNombre());
            existente.setContacto(proveedor.getContacto());
            return proveedorRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        proveedorRepository.deleteProveedorById(id);
    }
}
