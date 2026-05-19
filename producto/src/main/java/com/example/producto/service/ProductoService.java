package com.example.producto.service;

import com.example.producto.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Integer id);
    Producto save(Producto producto);
    Producto updateProducto(Integer id, Producto producto);
    void delete(Integer id);
}
