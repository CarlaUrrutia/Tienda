package com.example.inventario.service.impl;

import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.client.ProductoClient;
import com.example.inventario.client.TiendaClient;
import com.example.inventario.model.Inventario;
import com.example.inventario.repository.InventarioRepository;
import com.example.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired private InventarioRepository inventarioRepository;
    @Autowired private ProductoClient productoClient;
    @Autowired private TiendaClient tiendaClient;

    private InventarioDTO.Response toResponse(Inventario i) {
    return new InventarioDTO.Response(
        i.getId(), i.getCantidad(),
        tiendaClient.getTiendaById(i.getId_tienda()),
        productoClient.getProductoById(i.getId_producto())
    );
}

    @Override
    public List<InventarioDTO.Response> getAllInventarios() {
        return inventarioRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public InventarioDTO.Response getInventarioById(Integer id) {
        List<Inventario> lista = inventarioRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public InventarioDTO.Response save(InventarioDTO.Request request) {
        Inventario i = new Inventario();
        i.setCantidad(request.getCantidad());
        i.setId_producto(request.getId_producto());
        i.setId_tienda(request.getId_tienda());
        return toResponse(inventarioRepository.save(i));
    }

    @Override
    public InventarioDTO.Response updateInventario(Integer id, InventarioDTO.Request request) {
        List<Inventario> lista = inventarioRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Inventario i = lista.get(0);
        i.setCantidad(request.getCantidad());
        i.setId_producto(request.getId_producto());
        i.setId_tienda(request.getId_tienda());
        return toResponse(inventarioRepository.save(i));
    }

    @Override
    public void delete(Integer id) { inventarioRepository.deleteInventarioById(id); }
}
