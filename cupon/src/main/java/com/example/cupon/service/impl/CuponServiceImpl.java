package com.example.cupon.service.impl;

import com.example.cupon.model.Cupon;
import com.example.cupon.repository.CuponRepository;
import com.example.cupon.service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CuponServiceImpl implements CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public List<Cupon> getAllCupones() {
        return cuponRepository.findAll();
    }

    @Override
    public Cupon getCuponById(Integer id) {
        List<Cupon> lista = cuponRepository.buscarPorId(id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Cupon save(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    @Override
    public Cupon updateCupon(Integer id, Cupon cupon) {
        Cupon existente = getCuponById(id);
        if (existente != null) {
            existente.setCodigo(cupon.getCodigo());
            existente.setDescuento(cupon.getDescuento());
            existente.setFecha_expiracion(cupon.getFecha_expiracion());
            existente.setId_cliente(cupon.getId_cliente());
            return cuponRepository.save(existente);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        cuponRepository.deleteCuponById(id);
    }
}
