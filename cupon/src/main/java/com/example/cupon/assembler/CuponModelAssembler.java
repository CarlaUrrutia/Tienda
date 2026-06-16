package com.example.cupon.assembler;

import com.example.cupon.dto.CuponDTO;
import com.example.cupon.controller.CuponControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CuponModelAssembler implements RepresentationModelAssembler<CuponDTO.Response, EntityModel<CuponDTO.Response>> {

    @Override
    public EntityModel<CuponDTO.Response> toModel(CuponDTO.Response cupon) {
        return EntityModel.of(cupon,
            linkTo(methodOn(CuponControllerV2.class).buscarPorId(cupon.getId_cupon())).withSelfRel(),
            linkTo(methodOn(CuponControllerV2.class).listarTodos()).withRel("cupones")
        );
    }
}