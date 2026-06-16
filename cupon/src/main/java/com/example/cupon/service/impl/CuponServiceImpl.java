package com.example.cupon.service.impl;

import com.example.cupon.dto.CuponDTO;
import com.example.cupon.client.ClienteClient;
import com.example.cupon.model.Cupon;
import com.example.cupon.repository.CuponRepository;
import com.example.cupon.service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuponServiceImpl implements CuponService {

    @Autowired private CuponRepository cuponRepository;
    @Autowired private ClienteClient clienteClient;

    private CuponDTO.Response toResponse(Cupon c) {
        return new CuponDTO.Response(
            c.getId_cupon(), c.getCodigo(), c.getDescuento(), c.getFecha_expiracion(),
            clienteClient.getClienteById(c.getId_cliente())
        );
    }

    @Override
    public List<CuponDTO.Response> getAllCupones() {
        return cuponRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public CuponDTO.Response getCuponById(Integer id) {
        List<Cupon> lista = cuponRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public CuponDTO.Response save(CuponDTO.Request request) {
        Cupon c = new Cupon();
        c.setCodigo(request.getCodigo());
        c.setDescuento(request.getDescuento());
        c.setFecha_expiracion(request.getFecha_expiracion());
        c.setId_cliente(request.getId_cliente());
        return toResponse(cuponRepository.save(c));
    }

    @Override
    public CuponDTO.Response updateCupon(Integer id, CuponDTO.Request request) {
        List<Cupon> lista = cuponRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Cupon c = lista.get(0);
        c.setCodigo(request.getCodigo());
        c.setDescuento(request.getDescuento());
        c.setFecha_expiracion(request.getFecha_expiracion());
        c.setId_cliente(request.getId_cliente());
        return toResponse(cuponRepository.save(c));
    }

    @Override
    public void delete(Integer id) { cuponRepository.deleteCuponById(id); }
}
