package com.example.boleta.service.impl;

import com.example.boleta.DTO.BoletaDTO;
import com.example.boleta.client.ClienteClient;
import com.example.boleta.client.VentaClient;
import com.example.boleta.model.Boleta;
import com.example.boleta.repository.BoletaRepository;
import com.example.boleta.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoletaServiceImpl implements BoletaService {

    @Autowired private BoletaRepository boletaRepository;
    @Autowired private ClienteClient clienteClient;
    @Autowired private VentaClient ventaClient;

    private BoletaDTO.Response toResponse(Boleta b) {
        return new BoletaDTO.Response(
            b.getId_boleta(),
            clienteClient.getClienteById(b.getId_cliente()),
            ventaClient.getVentaById(b.getId_venta())
        );
    }

    @Override
    public List<BoletaDTO.Response> getAllBoletas() {
        return boletaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public BoletaDTO.Response getBoletaById(Integer id) {
        List<Boleta> lista = boletaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public BoletaDTO.Response save(BoletaDTO.Request request) {
        Boleta b = new Boleta();
        b.setId_cliente(request.getId_cliente());
        b.setId_venta(request.getId_venta());
        return toResponse(boletaRepository.save(b));
    }

    @Override
    public void delete(Integer id) { boletaRepository.deleteBoletaById(id); }
}
