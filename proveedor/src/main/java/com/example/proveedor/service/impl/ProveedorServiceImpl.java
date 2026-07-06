package com.example.proveedor.service.impl;

import com.example.proveedor.dto.ProveedorDTO;
import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepository;
import com.example.proveedor.service.ProveedorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired private ProveedorRepository proveedorRepository;

    private ProveedorDTO.Response toResponse(Proveedor p) {
        return new ProveedorDTO.Response(
            p.getId_proveedor(), p.getNombre(), p.getContacto()
        );
    }

    @Override
    public List<ProveedorDTO.Response> getAllProveedores() {
        return proveedorRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO.Response getProveedorById(Integer id) {
        List<Proveedor> lista = proveedorRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Proveedor con id " + id + " no encontrado");
        }
        return toResponse(lista.get(0));
    }

    @Override
    public ProveedorDTO.Response save(ProveedorDTO.Request request) {
        Proveedor p = new Proveedor();
        p.setNombre(request.getNombre());
        p.setContacto(request.getContacto());
        return toResponse(proveedorRepository.save(p));
    }

    @Override
    public ProveedorDTO.Response updateProveedor(Integer id, ProveedorDTO.Request request) {
        List<Proveedor> lista = proveedorRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Proveedor con id " + id + " no encontrado para actualizar");
        }
        Proveedor p = lista.get(0);
        p.setNombre(request.getNombre());
        p.setContacto(request.getContacto());
        return toResponse(proveedorRepository.save(p));
    }

    @Override
    public void delete(Integer id) {
        getProveedorById(id); // valida existencia antes de borrar
        proveedorRepository.deleteProveedorById(id);
    }
}