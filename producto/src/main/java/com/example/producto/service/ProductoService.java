package com.example.producto.service;

import com.example.producto.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO.Response> getAllProductos();
    ProductoDTO.Response getProductoById(Integer id);
    ProductoDTO.Response save(ProductoDTO.Request request);
    ProductoDTO.Response updateProducto(Integer id, ProductoDTO.Request request);
    void delete(Integer id);
}
