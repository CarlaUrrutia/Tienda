package com.example.factura.service;

import com.example.factura.dto.ClienteResponse;
import com.example.factura.dto.FacturaDTO;
import com.example.factura.dto.VentaResponse;
import com.example.factura.client.ClienteClient;
import com.example.factura.client.VentaClient;
import com.example.factura.model.Factura;
import com.example.factura.repository.FacturaRepository;
import com.example.factura.service.impl.FacturaServiceImpl;
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
class FacturaServiceImplTest {

    @Mock
    private FacturaRepository facturaRepository;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private VentaClient ventaClient;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private ClienteResponse clienteResponse;
    private VentaResponse ventaResponse;

    @BeforeEach
    void setUp() {
        factura = new Factura(1L, Date.valueOf("2024-01-15"), 15000, 20, 10);
        clienteResponse = new ClienteResponse(10, "Juan", "Pérez", "juan@mail.com");
        ventaResponse = new VentaResponse(20, Date.valueOf("2024-01-15"), 10, 5);
    }

    @Test
    @DisplayName("getAllFacturas - retorna lista de facturas enriquecidas")
    void getAllFacturas_retornaLista() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        List<FacturaDTO.Response> resultado = facturaService.getAllFacturas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(15000, resultado.get(0).getTotal());
    }

    @Test
    @DisplayName("getFacturaById - retorna factura cuando existe")
    void getFacturaById_existente_retornaFactura() {
        when(facturaRepository.buscarPorId(1)).thenReturn(List.of(factura));
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        FacturaDTO.Response resultado = facturaService.getFacturaById(1);

        assertNotNull(resultado);
        assertEquals(15000, resultado.getTotal());
    }

    @Test
    @DisplayName("getFacturaById - retorna null cuando no existe")
    void getFacturaById_noExistente_retornaNull() {
        when(facturaRepository.buscarPorId(99)).thenReturn(List.of());

        FacturaDTO.Response resultado = facturaService.getFacturaById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda factura y retorna respuesta enriquecida")
    void save_guardaFactura() {
        FacturaDTO.Request request = new FacturaDTO.Request(Date.valueOf("2024-01-15"), 15000, 20, 10);
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);
        when(clienteClient.getClienteById(10)).thenReturn(clienteResponse);
        when(ventaClient.getVentaById(20)).thenReturn(ventaResponse);

        FacturaDTO.Response resultado = facturaService.save(request);

        assertNotNull(resultado);
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(facturaRepository).deleteFacturaById(1);

        facturaService.delete(1);

        verify(facturaRepository, times(1)).deleteFacturaById(1);
    }
}
