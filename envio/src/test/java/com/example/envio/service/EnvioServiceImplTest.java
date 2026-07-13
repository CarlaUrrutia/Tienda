package com.example.envio.service;

import com.example.envio.dto.ClienteResponse;
import com.example.envio.dto.EmpleadoResponse;
import com.example.envio.dto.EnvioDTO;
import com.example.envio.dto.VentaResponse;
import com.example.envio.client.ClienteClient;
import com.example.envio.client.EmpleadoClient;
import com.example.envio.client.VentaClient;
import com.example.envio.model.Envio;
import com.example.envio.repository.EnvioRepository;
import com.example.envio.service.impl.EnvioServiceImpl;
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
class EnvioServiceImplTest {

    @Mock private EnvioRepository envioRepository;
    @Mock private VentaClient ventaClient;
    @Mock private ClienteClient clienteClient;
    @Mock private EmpleadoClient empleadoClient;

    @InjectMocks
    private EnvioServiceImpl envioService;

    private Envio envio;
    private VentaResponse ventaResponse;
    private ClienteResponse clienteResponse;
    private EmpleadoResponse empleadoResponse;

    @BeforeEach
    void setUp() {
        envio = new Envio(1, Date.valueOf("2024-02-01"), Date.valueOf("2024-02-05"),
                "Pendiente", 2, "Av. Siempre Viva 123", 20, 10, 5, 1);
        ventaResponse = new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com");
        empleadoResponse = new EmpleadoResponse(5, "Ana", "López", 50000);
    }

    @Test
    @DisplayName("getAllEnvios - retorna lista enriquecida")
    void getAllEnvios_retornaLista() {
        when(envioRepository.findAll()).thenReturn(List.of(envio));
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(empleadoClient.getEmpleadoById(5)).thenReturn(empleadoResponse);

        List<EnvioDTO.Response> resultado = envioService.getAllEnvios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Pendiente", resultado.get(0).getEstado());
    }

    @Test
    @DisplayName("getEnvioById - retorna envio cuando existe")
    void getEnvioById_existente() {
        when(envioRepository.buscarPorId(1)).thenReturn(List.of(envio));
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(empleadoClient.getEmpleadoById(5)).thenReturn(empleadoResponse);

        EnvioDTO.Response resultado = envioService.getEnvioById(1);

        assertNotNull(resultado);
        assertEquals("Pendiente", resultado.getEstado());
    }

    @Test
    @DisplayName("getEnvioById - retorna null cuando no existe")
    void getEnvioById_noExistente() {
        when(envioRepository.buscarPorId(99)).thenReturn(List.of());

        EnvioDTO.Response resultado = envioService.getEnvioById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(envioRepository).deleteEnvioById(1);

        envioService.delete(1);

        verify(envioRepository, times(1)).deleteEnvioById(1);
    }
}
