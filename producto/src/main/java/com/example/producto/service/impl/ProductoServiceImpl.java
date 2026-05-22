package com.example.producto.service.impl;

import com.example.producto.DTO.ProductoDTO;
import com.example.producto.client.ProveedorClient;
import com.example.producto.model.Producto;
import com.example.producto.repository.ProductoRepository;
import com.example.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private ProveedorClient proveedorClient;

    private ProductoDTO.Response toResponse(Producto p) {
        return new ProductoDTO.Response(
            p.getId_producto(), p.getNombre(), p.getPrecio_venta(),
            proveedorClient.getProveedorById(p.getId_proveedor())
        );
    }

    @Override
    public List<ProductoDTO.Response> getAllProductos() {
        return productoRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO.Response getProductoById(Integer id) {
        List<Producto> lista = productoRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public ProductoDTO.Response save(ProductoDTO.Request request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        p.setPrecio_venta(request.getPrecio_venta());
        p.setId_proveedor(request.getId_proveedor());
        return toResponse(productoRepository.save(p));
    }

    @Override
    public ProductoDTO.Response updateProducto(Integer id, ProductoDTO.Request request) {
        List<Producto> lista = productoRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Producto p = lista.get(0);
        p.setNombre(request.getNombre());
        p.setPrecio_venta(request.getPrecio_venta());
        p.setId_proveedor(request.getId_proveedor());
        return toResponse(productoRepository.save(p));
    }

    @Override
    public void delete(Integer id) { productoRepository.deleteProductoById(id); }
}
