package com.example.envio.controller;

import com.example.envio.dto.EnvioDTO;
import com.example.envio.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Envíos", description = "Gestión de envíos")
@RestController
@RequestMapping("/api/envios")
@Tag(name = "Envíos", description = "Operaciones CRUD sobre envíos")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    private void agregarLinks(EnvioDTO.Response r) {
        r.add(linkTo(methodOn(EnvioController.class).obtenerPorId(r.getId_envio())).withSelfRel());
        r.add(linkTo(methodOn(EnvioController.class).listar()).withRel("envios"));
        r.add(linkTo(methodOn(EnvioController.class).actualizar(r.getId_envio(), null)).withRel("actualizar"));
        r.add(linkTo(methodOn(EnvioController.class).eliminar(r.getId_envio())).withRel("eliminar"));
    }

    @Operation(summary = "Listar todos los envíos")
    @GetMapping
    public CollectionModel<EnvioDTO.Response> listar() {
        var envios = envioService.getAllEnvios();
        envios.forEach(this::agregarLinks);
        return CollectionModel.of(envios, linkTo(methodOn(EnvioController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Obtener un envío por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> obtenerPorId(@PathVariable Integer id) {
        EnvioDTO.Response r = envioService.getEnvioById(id);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Crear un nuevo envío")
    @PostMapping
    public ResponseEntity<EnvioDTO.Response> crear(@Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.save(request);
        if (r == null) return ResponseEntity.badRequest().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Actualizar un envío existente")
    @PutMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> actualizar(@PathVariable Integer id, @Valid @RequestBody EnvioDTO.Request request) {
        EnvioDTO.Response r = envioService.updateEnvio(id, request);
        if (r == null) return ResponseEntity.notFound().build();
        agregarLinks(r);
        return ResponseEntity.ok(r);
    }

    @Operation(summary = "Eliminar un envío")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.ok().build();
    }
}