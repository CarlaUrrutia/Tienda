package com.example.tarjeta.assembler;

import com.example.tarjeta.controller.TarjetaControllerV2;
import com.example.tarjeta.dto.TarjetaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TarjetaModelAssembler implements RepresentationModelAssembler<TarjetaDTO.Response, EntityModel<TarjetaDTO.Response>> {

    @Override
    public EntityModel<TarjetaDTO.Response> toModel(TarjetaDTO.Response tarjeta) {
        return EntityModel.of(tarjeta,
            linkTo(methodOn(TarjetaControllerV2.class).buscarPorId(tarjeta.getId_tarjeta())).withSelfRel(),
            linkTo(methodOn(TarjetaControllerV2.class).listarTodos()).withRel("tarjetas")
        );
    }
}