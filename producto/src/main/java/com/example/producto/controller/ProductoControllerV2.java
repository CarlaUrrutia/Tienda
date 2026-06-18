package com.example.producto.controller;

import com.example.producto.assembler.ProductoModelAssembler;
import com.example.producto.dto.ProductoDTO;
import com.example.producto.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/productos")
@RequiredArgsConstructor
public class ProductoControllerV2 {

    private final ProductoService productoService;
    private final ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ProductoDTO.Response>> listarTodos() {
        List<EntityModel<ProductoDTO.Response>> productos = productoService.getAllProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ProductoDTO.Response> buscarPorId(@PathVariable Integer id) {
        ProductoDTO.Response producto = productoService.getProductoById(id);
        return assembler.toModel(producto);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO.Response>> crear(@Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response nuevo = productoService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).buscarPorId(nuevo.getId_producto())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response actualizado = productoService.updateProducto(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}