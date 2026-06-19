package com.example.venta.service;

import com.example.venta.DTO.ClienteResponse;
import com.example.venta.DTO.EmpleadoResponse;
import com.example.venta.DTO.VentaDTO;
import com.example.venta.client.ClienteClient;
import com.example.venta.client.EmpleadoClient;
import com.example.venta.model.Venta;
import com.example.venta.repository.VentaRepository;
import com.example.venta.service.impl.VentaServiceImpl;
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
class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private EmpleadoClient empleadoClient;

    @InjectMocks
    private VentaServiceImpl ventaService;

    private Venta venta;
    private ClienteResponse clienteResponse;
    private EmpleadoResponse empleadoResponse;

    @BeforeEach
    void setUp() {
        venta = new Venta(1, Date.valueOf("2024-01-15"), 10, 5);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com");
        empleadoResponse = new EmpleadoResponse(5, "Ana", "López", 50000);
    }

    @Test
    @DisplayName("getAllVentas - retorna lista de ventas enriquecidas")
    void getAllVentas_retornaLista() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(empleadoClient.getEmpleadoById(5)).thenReturn(empleadoResponse);

        List<VentaDTO.Response> resultado = ventaService.getAllVentas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId_venta());
    }

    @Test
    @DisplayName("getVentaById - retorna venta cuando existe")
    void getVentaById_existente_retornaVenta() {
        when(ventaRepository.buscarPorId(1)).thenReturn(List.of(venta));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(empleadoClient.getEmpleadoById(5)).thenReturn(empleadoResponse);

        VentaDTO.Response resultado = ventaService.getVentaById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_venta());
    }

    @Test
    @DisplayName("getVentaById - retorna null cuando no existe")
    void getVentaById_noExistente_retornaNull() {
        when(ventaRepository.buscarPorId(99)).thenReturn(List.of());

        VentaDTO.Response resultado = ventaService.getVentaById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda venta y retorna respuesta enriquecida")
    void save_guardaVenta() {
        VentaDTO.Request request = new VentaDTO.Request(0, Date.valueOf("2024-01-15"), 10, 5);
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(empleadoClient.getEmpleadoById(5)).thenReturn(empleadoResponse);

        VentaDTO.Response resultado = ventaService.save(request);

        assertNotNull(resultado);
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(ventaRepository).deleteVentaById(1);

        ventaService.delete(1);

        verify(ventaRepository, times(1)).deleteVentaById(1);
    }
}
