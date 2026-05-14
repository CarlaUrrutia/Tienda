package com.ejemplo.persona.service.impl;

import com.ejemplo.persona.client.GeneroClient;
import com.ejemplo.persona.dto.GeneroDTO;
import com.ejemplo.persona.dto.PersonaDTO;
import com.ejemplo.persona.entity.Persona;
import com.ejemplo.persona.repository.PersonaRepository;
import com.ejemplo.persona.service.PersonaService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    /**
     * Feign inyecta aquí la implementación generada automáticamente.
     * Llamar a generoClient.buscarPorId(id) equivale a hacer:
     *   GET http://localhost:8081/api/generos/{id}  con Basic Auth
     */
    private final GeneroClient generoClient;

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO.Response> listarTodos() {
        log.info("[ms-persona] Listando todas las personas");
        return personaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaDTO.Response buscarPorId(Long id) {
        log.info("[ms-persona] Buscando persona id: {}", id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ms-persona] Persona no encontrada id: {}", id);
                    return new RuntimeException("Persona no encontrada con id: " + id);
                });
        return mapToResponse(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaDTO.Response buscarPorRut(String rut) {
        log.info("[ms-persona] Buscando persona RUT: {}", rut);
        Persona persona = personaRepository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con RUT: " + rut));
        return mapToResponse(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO.Response> buscarPorGenero(Long generoId) {
        log.info("[ms-persona] Buscando personas con generoId: {}", generoId);
        return personaRepository.findByGeneroId(generoId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonaDTO.Response crear(PersonaDTO.Request request) {
        log.info("[ms-persona] Creando persona RUT: {}", request.getRut());

        if (personaRepository.existsByRut(request.getRut())) {
            throw new RuntimeException("Ya existe una persona con el RUT: " + request.getRut());
        }

        // Verificamos que el género exista en ms-genero ANTES de guardar
        verificarGeneroExiste(request.getGeneroId());

        Persona persona = new Persona();
        persona.setRut(request.getRut());
        persona.setNombre(request.getNombre());
        persona.setEdad(request.getEdad());
        persona.setGeneroId(request.getGeneroId()); // Solo guardamos el ID

        Persona guardada = personaRepository.save(persona);
        log.info("[ms-persona] Persona creada id: {}", guardada.getId());
        return mapToResponse(guardada);
    }

    @Override
    @Transactional
    public PersonaDTO.Response actualizar(Long id, PersonaDTO.Request request) {
        log.info("[ms-persona] Actualizando persona id: {}", id);

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));

        verificarGeneroExiste(request.getGeneroId());

        persona.setRut(request.getRut());
        persona.setNombre(request.getNombre());
        persona.setEdad(request.getEdad());
        persona.setGeneroId(request.getGeneroId());

        return mapToResponse(personaRepository.save(persona));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("[ms-persona] Eliminando persona id: {}", id);
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("Persona no encontrada con id: " + id);
        }
        personaRepository.deleteById(id);
    }

    /**
     * Consulta ms-genero via Feign para validar que el género existe.
     * Si ms-genero retorna 404 o no está disponible, lanzamos excepción clara.
     */
    private void verificarGeneroExiste(Long generoId) {
        try {
            generoClient.buscarPorId(generoId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El género con id " + generoId + " no existe en ms-genero");
        } catch (FeignException e) {
            log.error("[ms-persona] Error al comunicarse con ms-genero: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con ms-genero: " + e.getMessage());
        }
    }

    /**
     * Mapeo Entity → DTO.
     * Aquí llamamos a Feign para obtener los datos del género y enriquecer la respuesta.
     */
    private PersonaDTO.Response mapToResponse(Persona persona) {
        GeneroDTO genero = null;

        try {
            // Llamada HTTP al microservicio de géneros
            genero = generoClient.buscarPorId(persona.getGeneroId());
            log.debug("[ms-persona] Género obtenido desde ms-genero: {}", genero.getDescripcion());
        } catch (FeignException e) {
            // Si ms-genero no responde, retornamos un objeto con solo el ID
            log.warn("[ms-persona] No se pudo obtener género id: {} - {}", persona.getGeneroId(), e.getMessage());
            genero = new GeneroDTO(persona.getGeneroId(), "No disponible");
        }

        return new PersonaDTO.Response(
                persona.getId(),
                persona.getRut(),
                persona.getNombre(),
                persona.getEdad(),
                genero
        );
    }
}
