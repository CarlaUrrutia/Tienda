package com.example.detalleventa.service;

import com.example.detalleventa.DTO.DetalleVentaDTO;
import com.example.detalleventa.dto.ProductoResponse;
import com.example.detalleventa.dto.VentaResponse;
import com.example.detalleventa.client.ProductoClient;
import com.example.detalleventa.client.VentaClient;
import com.example.detalleventa.model.DetalleVenta;
import com.example.detalleventa.repository.DetalleVentaRepository;
import com.example.detalleventa.service.impl.DetalleVentaServiceImpl;
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
class DetalleVentaServiceImplTest {

    @Mock private DetalleVentaRepository detalleVentaRepository;
    @Mock private VentaClient ventaClient;
    @Mock private ProductoClient productoClient;

    @InjectMocks
    private DetalleVentaServiceImpl detalleVentaService;

    private DetalleVenta detalle;
    private VentaResponse ventaResponse;
    private ProductoResponse productoResponse;

    @BeforeEach
    void setUp() {
        detalle = new DetalleVenta(1, 3, 5000, 20, 7);
        ventaResponse = new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5);
        productoResponse = new ProductoResponse(7, "Producto A", 5000);
    }

    @Test
    @DisplayName("getAllDetalles - retorna lista enriquecida")
    void getAllDetalles_retornaLista() {
        when(detalleVentaRepository.findAll()).thenReturn(List.of(detalle));
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);

        List<DetalleVentaDTO.Response> resultado = detalleVentaService.getAllDetalles();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(3, resultado.get(0).getCantidad());
    }

    @Test
    @DisplayName("getDetalleById - retorna detalle cuando existe")
    void getDetalleById_existente() {
        when(detalleVentaRepository.buscarPorId(1)).thenReturn(List.of(detalle));
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);

        DetalleVentaDTO.Response resultado = detalleVentaService.getDetalleById(1);

        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
    }

    @Test
    @DisplayName("getDetalleById - retorna null cuando no existe")
    void getDetalleById_noExistente() {
        when(detalleVentaRepository.buscarPorId(99)).thenReturn(List.of());

        DetalleVentaDTO.Response resultado = detalleVentaService.getDetalleById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("getDetallesByVenta - retorna detalles de una venta")
    void getDetallesByVenta_retornaLista() {
        when(detalleVentaRepository.buscarPorVenta(20)).thenReturn(List.of(detalle));
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);

        List<DetalleVentaDTO.Response> resultado = detalleVentaService.getDetallesByVenta(20);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(detalleVentaRepository).deleteDetalleById(1);

        detalleVentaService.delete(1);

        verify(detalleVentaRepository, times(1)).deleteDetalleById(1);
    }
}
