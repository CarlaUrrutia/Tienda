package com.example.inventario.service;

import com.example.inventario.DTO.InventarioDTO;
import com.example.inventario.DTO.ProductoResponse;
import com.example.inventario.DTO.TiendaResponse;
import com.example.inventario.client.ProductoClient;
import com.example.inventario.client.TiendaClient;
import com.example.inventario.model.Inventario;
import com.example.inventario.repository.InventarioRepository;
import com.example.inventario.service.impl.InventarioServiceImpl;
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
class InventarioServiceImplTest {

    @Mock private InventarioRepository inventarioRepository;
    @Mock private ProductoClient productoClient;
    @Mock private TiendaClient tiendaClient;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    private Inventario inventario;
    private ProductoResponse productoResponse;
    private TiendaResponse tiendaResponse;

    @BeforeEach
    void setUp() {
        inventario = new Inventario(1, 100, 7, 3);
        productoResponse = new ProductoResponse(7, "Producto A", 1000, null);
        tiendaResponse = new TiendaResponse(3, "Tienda Central", "Av. Principal", null, "Política general");
    }

    @Test
    @DisplayName("getAllInventarios - retorna lista enriquecida")
    void getAllInventarios_retornaLista() {
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        List<InventarioDTO.Response> resultado = inventarioService.getAllInventarios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getInventarioById - retorna inventario cuando existe")
    void getInventarioById_existente() {
        when(inventarioRepository.buscarPorId(1)).thenReturn(List.of(inventario));
        when(productoClient.getProductoById(7)).thenReturn(productoResponse);
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        InventarioDTO.Response resultado = inventarioService.getInventarioById(1);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("getInventarioById - retorna null cuando no existe")
    void getInventarioById_noExistente() {
        when(inventarioRepository.buscarPorId(99)).thenReturn(List.of());

        InventarioDTO.Response resultado = inventarioService.getInventarioById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(inventarioRepository).deleteInventarioById(1);

        inventarioService.delete(1);

        verify(inventarioRepository, times(1)).deleteInventarioById(1);
    }
}
