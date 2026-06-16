package com.example.boleta.assembler;

import com.example.boleta.controller.BoletaControllerV2;
import com.example.boleta.dto.BoletaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<BoletaDTO.Response, EntityModel<BoletaDTO.Response>> {

    @Override
    public EntityModel<BoletaDTO.Response> toModel(BoletaDTO.Response boleta) {
        return EntityModel.of(boleta,
            linkTo(methodOn(BoletaControllerV2.class).buscarPorId(boleta.getId_boleta())).withSelfRel(),
            linkTo(methodOn(BoletaControllerV2.class).listarTodos()).withRel("boletas")
        );
    }
}