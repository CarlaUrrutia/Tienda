package com.example.gerente.service;
import java.util.List;
public interface OfertaService {
    List<OfertaDTO.Reponse> listar();
    List<OfertaDTO.Response> buscarId(int id);
    OfertaDTO.Response crear(OfertaDTO.Request request);
    OfertaDTO.Response actualizar(int id,OfertaDTO.Request request);
    void eliminar(int id);
}