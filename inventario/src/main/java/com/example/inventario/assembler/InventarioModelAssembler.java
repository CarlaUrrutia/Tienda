package com.example.inventario.assembler;

import com.example.inventario.controller.InventarioControllerV2;
import com.example.inventario.dto.InventarioDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<InventarioDTO.Response, EntityModel<InventarioDTO.Response>> {

    @Override
    public EntityModel<InventarioDTO.Response> toModel(InventarioDTO.Response inventario) {
        return EntityModel.of(inventario,
            linkTo(methodOn(InventarioControllerV2.class).buscarPorId(inventario.getId_inventario())).withSelfRel(),
            linkTo(methodOn(InventarioControllerV2.class).listarTodos()).withRel("inventarios")
        );
    }
}