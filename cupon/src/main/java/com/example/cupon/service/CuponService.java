package com.example.cupon.service;

import com.example.cupon.model.Cupon;
import java.util.List;

public interface CuponService {
    List<Cupon> getAllCupones();
    Cupon getCuponById(Integer id);
    Cupon save(Cupon cupon);
    Cupon updateCupon(Integer id, Cupon cupon);
    void delete(Integer id);
}
