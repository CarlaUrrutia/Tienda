package com.example.oferta.assembler;

import com.example.oferta.controller.OfertaControllerV2;
import com.example.oferta.dto.OfertaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OfertaModelAssembler implements RepresentationModelAssembler<OfertaDTO.Response, EntityModel<OfertaDTO.Response>> {

    @Override
    public EntityModel<OfertaDTO.Response> toModel(OfertaDTO.Response oferta) {
        return EntityModel.of(oferta,
            linkTo(methodOn(OfertaControllerV2.class).buscarPorId(oferta.getId_oferta())).withSelfRel(),
            linkTo(methodOn(OfertaControllerV2.class).listarTodos()).withRel("ofertas")
        );
    }
}