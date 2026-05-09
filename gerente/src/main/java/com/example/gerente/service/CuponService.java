package com.example.gerente.service;
import java.util.List;
public interface CuponService {
    List<CuponDTO.Reponse> listarCupon();
    List<CuponDTO.Response> buscarIdCUpon(int id_cupon);
    CuponDTO.Response crear(CuponDTO.Request request);
    CuponDTO.Response actualizar(int id_cupon, CuponDTO.Request request);
    void eliminar(int id_cupon);

}
