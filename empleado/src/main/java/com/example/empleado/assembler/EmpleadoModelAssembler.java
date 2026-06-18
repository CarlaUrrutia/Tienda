package com.example.empleado.assembler;

import com.example.empleado.controller.EmpleadoControllerV2;
import com.example.empleado.dto.EmpleadoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoModelAssembler implements RepresentationModelAssembler<EmpleadoDTO.Response, EntityModel<EmpleadoDTO.Response>> {

    @Override
    public EntityModel<EmpleadoDTO.Response> toModel(EmpleadoDTO.Response empleado) {
        return EntityModel.of(empleado,
            linkTo(methodOn(EmpleadoControllerV2.class).buscarPorId(empleado.getId_empleado())).withSelfRel(),
            linkTo(methodOn(EmpleadoControllerV2.class).listarTodos()).withRel("empleados")
        );
    }
}