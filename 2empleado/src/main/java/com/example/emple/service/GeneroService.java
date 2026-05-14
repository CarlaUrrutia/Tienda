package com.ejemplo.genero.service;

import com.ejemplo.genero.dto.GeneroDTO;
import java.util.List;

public interface GeneroService {
    List<GeneroDTO.Response> listarTodos();
    GeneroDTO.Response buscarPorId(Long id);
    GeneroDTO.Response crear(GeneroDTO.Request request);
    GeneroDTO.Response actualizar(Long id, GeneroDTO.Request request);
    void eliminar(Long id);
}
