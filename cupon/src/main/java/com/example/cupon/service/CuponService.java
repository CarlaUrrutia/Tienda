package com.example.cupon.service;

import com.example.cupon.dto.CuponDTO;
import java.util.List;

public interface CuponService {
    List<CuponDTO.Response> getAllCupones();
    CuponDTO.Response getCuponById(Integer id);
    CuponDTO.Response save(CuponDTO.Request request);
    CuponDTO.Response updateCupon(Integer id, CuponDTO.Request request);
    void delete(Integer id);
}
