package com.example.envio.assembler;

import com.example.envio.controller.EnvioControllerV2;
import com.example.envio.dto.EnvioDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<EnvioDTO.Response, EntityModel<EnvioDTO.Response>> {

    @Override
    public EntityModel<EnvioDTO.Response> toModel(EnvioDTO.Response envio) {
        return EntityModel.of(envio,
            linkTo(methodOn(EnvioControllerV2.class).buscarPorId(envio.getId_envio())).withSelfRel(),
            linkTo(methodOn(EnvioControllerV2.class).listarTodos()).withRel("envios")
        );
    }
}