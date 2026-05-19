package com.example.producto.service.impl;

import com.example.producto.model.Producto;
import com.example.producto.repository.ProductoRepository;
import com.example.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Integer id) {
        List<Producto> lista = productoRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Integer id, Producto producto) {
        Producto existente = getProductoById(id);
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setPrecio_venta(producto.getPrecio_venta());
            existente.setId_proveedor(producto.getId_proveedor());
            return productoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteProductoById(id);
    }
}
