package com.example.oferta.service;

import com.example.oferta.DTO.OfertaDTO;
import com.example.oferta.DTO.ProductoResponse;
import com.example.oferta.client.ProductoClient;
import com.example.oferta.model.Oferta;
import com.example.oferta.repository.OfertaRepository;
import com.example.oferta.service.impl.OfertaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfertaServiceImplTest {

    @Mock private OfertaRepository ofertaRepository;
    @Mock private ProductoClient productoClient;

    @InjectMocks
    private OfertaServiceImpl ofertaService;

    private Oferta oferta;
    private ProductoResponse productoResponse;

    @BeforeEach
    void setUp() {
        oferta = new Oferta(1, "Oferta verano 20 por ciento", 20, 7);
        productoResponse = new ProductoResponse(7, "Producto A", 1000, null);
    }

    @Test
    @DisplayName("getAllOfertas - retorna lista enriquecida")
    void getAllOfertas_retornaLista() {
        when(ofertaRepository.findAll()).thenReturn(List.of(oferta));
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);

        List<OfertaDTO.Response> resultado = ofertaService.getAllOfertas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(20, resultado.get(0).getDescuento());
    }

    @Test
    @DisplayName("getOfertaById - retorna oferta cuando existe")
    void getOfertaById_existente() {
        when(ofertaRepository.buscarPorId(1)).thenReturn(List.of(oferta));
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);

        OfertaDTO.Response resultado = ofertaService.getOfertaById(1);

        assertNotNull(resultado);
        assertEquals(20, resultado.getDescuento());
    }

    @Test
    @DisplayName("getOfertaById - retorna null cuando no existe")
    void getOfertaById_noExistente() {
        when(ofertaRepository.buscarPorId(99)).thenReturn(List.of());

        OfertaDTO.Response resultado = ofertaService.getOfertaById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(ofertaRepository).deleteOfertaById(1);

        ofertaService.delete(1);

        verify(ofertaRepository, times(1)).deleteOfertaById(1);
    }
}
