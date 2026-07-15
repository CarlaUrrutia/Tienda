package com.example.cupon.service;

import com.example.cupon.DTO.ClienteResponse;
import com.example.cupon.DTO.CuponDTO;
import com.example.cupon.client.ClienteClient;
import com.example.cupon.model.Cupon;
import com.example.cupon.repository.CuponRepository;
import com.example.cupon.service.impl.CuponServiceImpl;
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
class CuponServiceImplTest {

    @Mock private CuponRepository cuponRepository;
    @Mock private ClienteClient clienteClient;

    @InjectMocks
    private CuponServiceImpl cuponService;

    private Cupon cupon;
    private ClienteResponse clienteResponse;

    @BeforeEach
    void setUp() {
        cupon = new Cupon(1, "DESC20", 20, Date.valueOf("2025-12-31"), 10);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com");
    }

    @Test
    @DisplayName("getAllCupones - retorna lista de cupones")
    void getAllCupones_retornaLista() {
        when(cuponRepository.findAll()).thenReturn(List.of(cupon));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);

        List<CuponDTO.Response> resultado = cuponService.getAllCupones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getCuponById - retorna cupon cuando existe")
    void getCuponById_existente() {
        when(cuponRepository.buscarPorId(1)).thenReturn(List.of(cupon));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);

        CuponDTO.Response resultado = cuponService.getCuponById(1);

        assertNotNull(resultado);
        assertEquals("DESC20", resultado.getCodigo());
    }

    @Test
    @DisplayName("getCuponById - retorna null cuando no existe")
    void getCuponById_noExistente() {
        when(cuponRepository.buscarPorId(99)).thenReturn(List.of());

        CuponDTO.Response resultado = cuponService.getCuponById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda cupon y retorna respuesta")
    void save_guardaCupon() {
        CuponDTO.Request request = new CuponDTO.Request("DESC20", 20, Date.valueOf("2025-12-31"), 10);
        when(cuponRepository.save(any(Cupon.class))).thenReturn(cupon);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);

        CuponDTO.Response resultado = cuponService.save(request);

        assertNotNull(resultado);
        verify(cuponRepository, times(1)).save(any(Cupon.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(cuponRepository).deleteCuponById(1);

        cuponService.delete(1);

        verify(cuponRepository, times(1)).deleteCuponById(1);
    }
}
