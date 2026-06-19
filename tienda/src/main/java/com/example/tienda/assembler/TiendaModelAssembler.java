package com.example.tienda.assembler;

import com.example.tienda.controller.TiendaControllerV2;
import com.example.tienda.dto.TiendaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TiendaModelAssembler implements RepresentationModelAssembler<TiendaDTO.Response, EntityModel<TiendaDTO.Response>> {

    @Override
    public EntityModel<TiendaDTO.Response> toModel(TiendaDTO.Response tienda) {
        return EntityModel.of(tienda,
            linkTo(methodOn(TiendaControllerV2.class).buscarPorId(tienda.getId_tienda())).withSelfRel(),
            linkTo(methodOn(TiendaControllerV2.class).listarTodos()).withRel("tiendas")
        );
    }
}