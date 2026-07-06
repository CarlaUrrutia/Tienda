package com.example.oferta.service.impl;

import com.example.oferta.dto.OfertaDTO;
import com.example.oferta.client.ProductoClient;
import com.example.oferta.model.Oferta;
import com.example.oferta.repository.OfertaRepository;
import com.example.oferta.service.OfertaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired private OfertaRepository ofertaRepository;
    @Autowired private ProductoClient productoClient;

    private OfertaDTO.Response toResponse(Oferta o) {
        return new OfertaDTO.Response(
            o.getId(), o.getDescripcion(), o.getDescuento(),
            o.getFecha_inicio(), o.getFecha_fin(),
            productoClient.getProductoById(o.getId_producto())
        );
    }

    @Override
    public List<OfertaDTO.Response> getAllOfertas() {
        return ofertaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public OfertaDTO.Response getOfertaById(Integer id) {
        List<Oferta> lista = ofertaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Oferta con id " + id + " no encontrada");
        }
        return toResponse(lista.get(0));
    }

    @Override
    public OfertaDTO.Response save(OfertaDTO.Request request) {
        Oferta o = new Oferta();
        o.setDescripcion(request.getDescripcion());
        o.setDescuento(request.getDescuento());
        o.setFecha_inicio(request.getFecha_inicio());
        o.setFecha_fin(request.getFecha_fin());
        o.setId_producto(request.getId_producto());
        return toResponse(ofertaRepository.save(o));
    }

    @Override
    public OfertaDTO.Response updateOferta(Integer id, OfertaDTO.Request request) {
        List<Oferta> lista = ofertaRepository.buscarPorId(id);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Oferta con id " + id + " no encontrada para actualizar");
        }
        Oferta o = lista.get(0);
        o.setDescripcion(request.getDescripcion());
        o.setDescuento(request.getDescuento());
        o.setFecha_inicio(request.getFecha_inicio());
        o.setFecha_fin(request.getFecha_fin());
        o.setId_producto(request.getId_producto());
        return toResponse(ofertaRepository.save(o));
    }

    @Override
    public void delete(Integer id) {
        getOfertaById(id); // valida existencia antes de borrar
        ofertaRepository.deleteOfertaById(id);
    }
}