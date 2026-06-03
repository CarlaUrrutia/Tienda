package com.example.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gerente.model.Inventario;
import com.example.gerente.model.Oferta;
import com.example.gerente.model.Producto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired private ProductoService productoService;

    @GetMapping
    public List<ProductoDTO.Response> listar() { return productoService.getAllProductos(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> obtenerPorId(@PathVariable Integer id) {
        ProductoDTO.Response r = productoService.getProductoById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO.Response> crear(@Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response r = productoService.save(request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response r = productoService.updateProducto(id, request);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
