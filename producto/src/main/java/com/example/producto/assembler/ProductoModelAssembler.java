package com.example.producto.assembler;

import com.example.producto.controller.ProductoControllerV2;
import com.example.producto.dto.ProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoDTO.Response, EntityModel<ProductoDTO.Response>> {

    @Override
    public EntityModel<ProductoDTO.Response> toModel(ProductoDTO.Response producto) {
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoControllerV2.class).buscarPorId(producto.getId_producto())).withSelfRel(),
            linkTo(methodOn(ProductoControllerV2.class).listarTodos()).withRel("productos")
        );
    }
}