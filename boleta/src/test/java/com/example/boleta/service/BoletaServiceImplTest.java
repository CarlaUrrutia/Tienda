package com.example.boleta.service;

import com.example.boleta.client.ClienteClient;
import com.example.boleta.client.VentaClient;
import com.example.boleta.dto.BoletaDTO;
import com.example.boleta.dto.ClienteResponse;
import com.example.boleta.dto.VentaResponse;
import com.example.boleta.model.Boleta;
import com.example.boleta.repository.BoletaRepository;
import com.example.boleta.service.impl.BoletaServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoletaServiceImplTest {

    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private ClienteClient clienteClient;

    @Mock
    private VentaClient ventaClient;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    private Boleta boleta;
    private ClienteResponse clienteResponse;
    private VentaResponse ventaResponse;

    @BeforeEach
    void setUp() {
        boleta = new Boleta(1, 10, 20);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez López", "juan@mail.com");
        ventaResponse = new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5);
    }

    @Test
    @DisplayName("getAllBoletas - retorna lista con todas las boletas enriquecidas")
    void getAllBoletas_retornaLista() {
        when(boletaRepository.findAll()).thenReturn(List.of(boleta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        List<BoletaDTO.Response> resultado = boletaService.getAllBoletas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId_boleta());
        assertEquals("Juan", resultado.get(0).getCliente().getNombre());
        assertEquals(20, resultado.get(0).getVenta().getId_venta());
    }

    @Test
    @DisplayName("getBoletaById - retorna boleta cuando existe")
    void getBoletaById_existente_retornaBoleta() {
        when(boletaRepository.buscarPorId(1)).thenReturn(List.of(boleta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        BoletaDTO.Response resultado = boletaService.getBoletaById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_boleta());
    }

    @Test
    @DisplayName("getBoletaById - lanza excepción cuando no existe")
    void getBoletaById_noExistente_lanzaExcepcion() {
        when(boletaRepository.buscarPorId(99)).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> boletaService.getBoletaById(99));
    }

    @Test
    @DisplayName("save - guarda boleta y retorna respuesta enriquecida")
    void save_guardaBoleta() {
        BoletaDTO.Request request = new BoletaDTO.Request(10, 20);
        when(boletaRepository.save(any(Boleta.class))).thenReturn(boleta);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        BoletaDTO.Response resultado = boletaService.save(request);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_boleta());
        assertNotNull(resultado.getCliente());
        assertNotNull(resultado.getVenta());
        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(boletaRepository).deleteBoletaById(1);

        boletaService.delete(1);

        verify(boletaRepository, times(1)).deleteBoletaById(1);
    }
}
