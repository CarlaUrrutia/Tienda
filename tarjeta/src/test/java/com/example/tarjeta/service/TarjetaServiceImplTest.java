package com.example.tarjeta.service;

import com.example.tarjeta.DTO.ClienteResponse;
import com.example.tarjeta.DTO.TarjetaDTO;
import com.example.tarjeta.client.ClienteClient;
import com.example.tarjeta.model.Tarjeta;
import com.example.tarjeta.repository.TarjetaRepository;
import com.example.tarjeta.service.impl.TarjetaServiceImpl;
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
class TarjetaServiceImplTest {

    @Mock private TarjetaRepository tarjetaRepository;
    @Mock private ClienteClient clienteClient;

    @InjectMocks
    private TarjetaServiceImpl tarjetaService;

    private Tarjeta tarjeta;
    private ClienteResponse clienteResponse;

    @BeforeEach
    void setUp() {
        tarjeta = new Tarjeta(1, 'V', 10);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com");
    }

    @Test
    @DisplayName("getAllTarjetas - retorna lista enriquecida")
    void getAllTarjetas_retornaLista() {
        when(tarjetaRepository.findAll()).thenReturn(List.of(tarjeta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);

        List<TarjetaDTO.Response> resultado = tarjetaService.getAllTarjetas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getTarjetaById - retorna tarjeta cuando existe")
    void getTarjetaById_existente() {
        when(tarjetaRepository.buscarPorId(1)).thenReturn(List.of(tarjeta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);

        TarjetaDTO.Response resultado = tarjetaService.getTarjetaById(1);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("getTarjetaById - retorna null cuando no existe")
    void getTarjetaById_noExistente() {
        when(tarjetaRepository.buscarPorId(99)).thenReturn(List.of());

        TarjetaDTO.Response resultado = tarjetaService.getTarjetaById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(tarjetaRepository).deleteTarjetaById(1);

        tarjetaService.delete(1);

        verify(tarjetaRepository, times(1)).deleteTarjetaById(1);
    }
}
