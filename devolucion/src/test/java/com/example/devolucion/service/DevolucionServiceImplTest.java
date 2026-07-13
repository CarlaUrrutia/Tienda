package com.example.devolucion.service;

import com.example.devolucion.DTO.DevolucionDTO;
import com.example.devolucion.client.*;
import com.example.devolucion.dto.*;
import com.example.devolucion.model.Devolucion;
import com.example.devolucion.repository.DevolucionRepository;
import com.example.devolucion.service.impl.DevolucionServiceImpl;
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
class DevolucionServiceImplTest {

    @Mock private DevolucionRepository devolucionRepository;
    @Mock private EmpleadoClient empleadoClient;
    @Mock private ClienteClient clienteClient;
    @Mock private TarjetaClient tarjetaClient;
    @Mock private VentaClient ventaClient;
    @Mock private ProductoClient productoClient;

    @InjectMocks
    private DevolucionServiceImpl devolucionService;

    private Devolucion devolucion;

    @BeforeEach
    void setUp() {
        devolucion = new Devolucion(1, Date.valueOf("2024-03-01"), "Producto dañado", 5000, 2, 5, 10, 3, 20, 7);
    }

    @Test
    @DisplayName("getAllDevoluciones - retorna lista enriquecida")
    void getAllDevoluciones_retornaLista() {
        when(devolucionRepository.findAll()).thenReturn(List.of(devolucion));
        when(empleadoClient.getEmpleadoById(5)).thenReturn(new EmpleadoResponse(5, "Ana", "López", 50000));
        when(clienteClient.getClienteById(10)).thenReturn(new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com"));
        when(tarjetaClient.getTarjetaById(3)).thenReturn(new TarjetaResponse(3, 'V', 10));
        when(ventaClient.getVentaById(20)).thenReturn(new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5));
        when(productoClient.getProductoById(7)).thenReturn(new ProductoResponse(7, "Producto A", 1000));

        List<DevolucionDTO.Response> resultado = devolucionService.getAllDevoluciones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Producto dañado", resultado.get(0).getMotivo());
    }

    @Test
    @DisplayName("getDevolucionById - retorna devolucion cuando existe")
    void getDevolucionById_existente() {
        when(devolucionRepository.buscarPorId(1)).thenReturn(List.of(devolucion));
        when(empleadoClient.getEmpleadoById(5)).thenReturn(new EmpleadoResponse(5, "Ana", "López", 50000));
        when(clienteClient.getClienteById(10)).thenReturn(new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com"));
        when(tarjetaClient.getTarjetaById(3)).thenReturn(new TarjetaResponse(3, 'V', 10));
        when(ventaClient.getVentaById(20)).thenReturn(new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5));
        when(productoClient.getProductoById(7)).thenReturn(new ProductoResponse(7, "Producto A", 1000));

        DevolucionDTO.Response resultado = devolucionService.getDevolucionById(1);

        assertNotNull(resultado);
        assertEquals("Producto dañado", resultado.getMotivo());
    }

    @Test
    @DisplayName("getDevolucionById - retorna null cuando no existe")
    void getDevolucionById_noExistente() {
        when(devolucionRepository.buscarPorId(99)).thenReturn(List.of());

        DevolucionDTO.Response resultado = devolucionService.getDevolucionById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(devolucionRepository).deleteDevolucionById(1);

        devolucionService.delete(1);

        verify(devolucionRepository, times(1)).deleteDevolucionById(1);
    }
}
