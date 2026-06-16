package com.example.cliente.assembler;

import com.example.cliente.controller.ClienteControllerV2;
import com.example.cliente.dto.ClienteDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteDTO.Response, EntityModel<ClienteDTO.Response>> {

    @Override
    public EntityModel<ClienteDTO.Response> toModel(ClienteDTO.Response cliente) {
        return EntityModel.of(cliente,
            linkTo(methodOn(ClienteControllerV2.class).buscarPorId(cliente.getId_cliente())).withSelfRel(),
            linkTo(methodOn(ClienteControllerV2.class).listarTodos()).withRel("clientes")
        );
    }
}