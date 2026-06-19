package com.example.rol.assembler;

import com.example.rol.controller.RolControllerV2;
import com.example.rol.dto.RolDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RolModelAssembler implements RepresentationModelAssembler<RolDTO.Response, EntityModel<RolDTO.Response>> {

    @Override
    public EntityModel<RolDTO.Response> toModel(RolDTO.Response rol) {
        return EntityModel.of(rol,
            linkTo(methodOn(RolControllerV2.class).buscarPorId(rol.getId_rol())).withSelfRel(),
            linkTo(methodOn(RolControllerV2.class).listarTodos()).withRel("roles")
        );
    }
}