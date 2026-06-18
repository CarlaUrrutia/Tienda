package com.example.factura.assembler;

import com.example.factura.controller.FacturaControllerV2;
import com.example.factura.dto.FacturaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FacturaModelAssembler implements RepresentationModelAssembler<FacturaDTO.Response, EntityModel<FacturaDTO.Response>> {

    @Override
    public EntityModel<FacturaDTO.Response> toModel(FacturaDTO.Response factura) {
        return EntityModel.of(factura,
            linkTo(methodOn(FacturaControllerV2.class).buscarPorId(factura.getId_factura())).withSelfRel(),
            linkTo(methodOn(FacturaControllerV2.class).listarTodos()).withRel("facturas")
        );
    }
}