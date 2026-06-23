package com.example.envio.controller;

import com.example.envio.assembler.EnvioModelAssembler;
import com.example.envio.dto.EnvioDTO;
import com.example.envio.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/envios")
@RequiredArgsConstructor
public class EnvioControllerV2 {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<EnvioDTO.Response>> listarTodos() {
        List<EntityModel<EnvioDTO.Response>> envios = envioService.getAllEnvios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).listarTodos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<EnvioDTO.Response> buscarPorId(@PathVariable Integer id) {
        EnvioDTO.Response envio = envioService.getEnvioById(id);
        return assembler.toModel(envio);
    }

    @PostMapping(produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EnvioDTO.Response>> crear(@Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response nuevo = envioService.save(request);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).buscarPorId(nuevo.getId_envio())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<EnvioDTO.Response>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response actualizado = envioService.updateEnvio(id, request);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}