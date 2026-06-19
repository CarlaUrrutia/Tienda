package com.example.venta.assembler;

import com.example.venta.controller.VentaControllerV2;
import com.example.venta.dto.VentaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<VentaDTO.Response, EntityModel<VentaDTO.Response>> {

    @Override
    public EntityModel<VentaDTO.Response> toModel(VentaDTO.Response venta) {
        return EntityModel.of(venta,
            linkTo(methodOn(VentaControllerV2.class).buscarPorId(venta.getId_venta())).withSelfRel(),
            linkTo(methodOn(VentaControllerV2.class).listarTodos()).withRel("ventas")
        );
    }
}