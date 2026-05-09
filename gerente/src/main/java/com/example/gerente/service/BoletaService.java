package com.example.gerente.service;
import java.util.List;
public interface BoletaService {
    List<BoletaDTO.Reponse> listar();
    BoletaDTO.Response buscarPorId(int id_boleta);
    BoletaDTO.Response crear (BoletaDTO.Request request)
    BoletaDTO .Reponse  actualizar (int id_boleta , BoletaDTO.Request request)
    void eliminar (int id_boleta);

}
