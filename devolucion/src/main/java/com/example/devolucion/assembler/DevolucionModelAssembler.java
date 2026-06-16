package com.example.devolucion.assembler;

import com.example.devolucion.controller.DevolucionControllerV2;
import com.example.devolucion.dto.DevolucionDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DevolucionModelAssembler implements RepresentationModelAssembler<DevolucionDTO.Response, EntityModel<DevolucionDTO.Response>> {

    @Override
    public EntityModel<DevolucionDTO.Response> toModel(DevolucionDTO.Response devolucion) {
        return EntityModel.of(devolucion,
            linkTo(methodOn(DevolucionControllerV2.class).buscarPorId(devolucion.getId_devolucion())).withSelfRel(),
            linkTo(methodOn(DevolucionControllerV2.class).listarTodos()).withRel("devoluciones")
        );
    }
}