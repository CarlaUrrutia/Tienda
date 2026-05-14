package com.ejemplo.genero.service.impl;

import com.ejemplo.genero.dto.GeneroDTO;
import com.ejemplo.genero.entity.Genero;
import com.ejemplo.genero.repository.GeneroRepository;
import com.ejemplo.genero.service.GeneroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GeneroDTO.Response> listarTodos() {
        log.info("[ms-genero] Listando todos los géneros");
        return generoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GeneroDTO.Response buscarPorId(Long id) {
        log.info("[ms-genero] Buscando género id: {}", id);
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ms-genero] Género no encontrado id: {}", id);
                    return new RuntimeException("Género no encontrado con id: " + id);
                });
        return mapToResponse(genero);
    }

    @Override
    @Transactional
    public GeneroDTO.Response crear(GeneroDTO.Request request) {
        log.info("[ms-genero] Creando género: {}", request.getDescripcion());

        if (generoRepository.existsByDescripcionIgnoreCase(request.getDescripcion())) {
            throw new RuntimeException("Ya existe un género: " + request.getDescripcion());
        }

        Genero genero = new Genero();
        genero.setDescripcion(request.getDescripcion());

        Genero guardado = generoRepository.save(genero);
        log.info("[ms-genero] Género creado id: {}", guardado.getId());
        return mapToResponse(guardado);
    }

    @Override
    @Transactional
    public GeneroDTO.Response actualizar(Long id, GeneroDTO.Request request) {
        log.info("[ms-genero] Actualizando género id: {}", id);
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Género no encontrado con id: " + id));

        genero.setDescripcion(request.getDescripcion());
        return mapToResponse(generoRepository.save(genero));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("[ms-genero] Eliminando género id: {}", id);
        if (!generoRepository.existsById(id)) {
            throw new RuntimeException("Género no encontrado con id: " + id);
        }
        generoRepository.deleteById(id);
    }

    private GeneroDTO.Response mapToResponse(Genero genero) {
        return new GeneroDTO.Response(genero.getId(), genero.getDescripcion());
    }
}
