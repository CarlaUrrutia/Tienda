package com.ejemplo.persona.service;

import com.ejemplo.persona.dto.PersonaDTO;
import java.util.List;

public interface PersonaService {
    List<PersonaDTO.Response> listarTodos();
    PersonaDTO.Response buscarPorId(Long id);
    PersonaDTO.Response buscarPorRut(String rut);
    List<PersonaDTO.Response> buscarPorGenero(Long generoId);
    PersonaDTO.Response crear(PersonaDTO.Request request);
    PersonaDTO.Response actualizar(Long id, PersonaDTO.Request request);
    void eliminar(Long id);
}
