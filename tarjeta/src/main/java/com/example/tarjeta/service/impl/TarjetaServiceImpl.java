package com.example.tarjeta.service.impl;

import com.example.tarjeta.DTO.TarjetaDTO;
import com.example.tarjeta.client.ClienteClient;
import com.example.tarjeta.model.Tarjeta;
import com.example.tarjeta.repository.TarjetaRepository;
import com.example.tarjeta.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired private TarjetaRepository tarjetaRepository;
    @Autowired private ClienteClient clienteClient;

    private TarjetaDTO.Response toResponse(Tarjeta t) {
        return new TarjetaDTO.Response(
            t.getId_tarjeta(), t.getTipo(),
            clienteClient.getClienteById(t.getId_cliente())
        );
    }

    @Override
    public List<TarjetaDTO.Response> getAllTarjetas() {
        return tarjetaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TarjetaDTO.Response getTarjetaById(Integer id) {
        List<Tarjeta> lista = tarjetaRepository.buscarPorId(id);
        return lista.isEmpty() ? null : toResponse(lista.get(0));
    }

    @Override
    public TarjetaDTO.Response save(TarjetaDTO.Request request) {
        Tarjeta t = new Tarjeta();
        t.setTipo(request.getTipo());
        t.setId_cliente(request.getId_cliente());
        return toResponse(tarjetaRepository.save(t));
    }

    @Override
    public TarjetaDTO.Response updateTarjeta(Integer id, TarjetaDTO.Request request) {
        List<Tarjeta> lista = tarjetaRepository.buscarPorId(id);
        if (lista.isEmpty()) return null;
        Tarjeta t = lista.get(0);
        t.setTipo(request.getTipo());
        t.setId_cliente(request.getId_cliente());
        return toResponse(tarjetaRepository.save(t));
    }

    @Override
    public void delete(Integer id) { tarjetaRepository.deleteTarjetaById(id); }
}
