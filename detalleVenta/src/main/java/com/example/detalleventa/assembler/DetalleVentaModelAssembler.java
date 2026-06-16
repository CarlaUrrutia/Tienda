package com.example.detalleventa.assembler;

import com.example.detalleventa.controller.DetalleVentaControllerV2;
import com.example.detalleventa.dto.DetalleVentaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DetalleVentaModelAssembler implements RepresentationModelAssembler<DetalleVentaDTO.Response, EntityModel<DetalleVentaDTO.Response>> {

    @Override
    public EntityModel<DetalleVentaDTO.Response> toModel(DetalleVentaDTO.Response detalle) {
        return EntityModel.of(detalle,
            linkTo(methodOn(DetalleVentaControllerV2.class).buscarPorId(detalle.getId_detalle())).withSelfRel(),
            linkTo(methodOn(DetalleVentaControllerV2.class).listarTodos()).withRel("detalles")
        );
    }
}