package com.example.proveedor.assembler;

import com.example.proveedor.controller.ProveedorControllerV2;
import com.example.proveedor.dto.ProveedorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<ProveedorDTO.Response, EntityModel<ProveedorDTO.Response>> {

    @Override
    public EntityModel<ProveedorDTO.Response> toModel(ProveedorDTO.Response proveedor) {
        return EntityModel.of(proveedor,
            linkTo(methodOn(ProveedorControllerV2.class).buscarPorId(proveedor.getId_proveedor())).withSelfRel(),
            linkTo(methodOn(ProveedorControllerV2.class).listarTodos()).withRel("proveedores")
        );
    }
}