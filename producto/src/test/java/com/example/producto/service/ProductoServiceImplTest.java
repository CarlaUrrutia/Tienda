package com.example.producto.service;

import com.example.producto.dto.ProductoDTO;
import com.example.producto.dto.ProveedorResponse;
import com.example.producto.client.ProveedorClient;
import com.example.producto.model.Producto;
import com.example.producto.repository.ProductoRepository;
import com.example.producto.service.impl.ProductoServiceImpl;
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
class ProductoServiceImplTest {

    @Mock private ProductoRepository productoRepository;
    @Mock private ProveedorClient proveedorClient;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private ProveedorResponse proveedorResponse;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Camisa", 15000, 4);
        proveedorResponse = new ProveedorResponse(4, "Proveedor SA", "contacto@prov.com");
    }

    @Test
    @DisplayName("getAllProductos - retorna lista enriquecida")
    void getAllProductos_retornaLista() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));
        when(proveedorClient.getProveedorById(4)).thenReturn(proveedorResponse);

        List<ProductoDTO.Response> resultado = productoService.getAllProductos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Camisa", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("getProductoById - retorna producto cuando existe")
    void getProductoById_existente() {
        when(productoRepository.buscarPorId(1)).thenReturn(List.of(producto));
        when(proveedorClient.getProveedorById(4)).thenReturn(proveedorResponse);

        ProductoDTO.Response resultado = productoService.getProductoById(1);

        assertNotNull(resultado);
        assertEquals("Camisa", resultado.getNombre());
    }

    @Test
    @DisplayName("getProductoById - retorna null cuando no existe")
    void getProductoById_noExistente() {
        when(productoRepository.buscarPorId(99)).thenReturn(List.of());

        ProductoDTO.Response resultado = productoService.getProductoById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda producto y retorna respuesta enriquecida")
    void save_guardaProducto() {
        ProductoDTO.Request request = new ProductoDTO.Request(0, "Camisa", 15000, 4);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        when(proveedorClient.getProveedorById(4)).thenReturn(proveedorResponse);

        ProductoDTO.Response resultado = productoService.save(request);

        assertNotNull(resultado);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(productoRepository).deleteProductoById(1);

        productoService.delete(1);

        verify(productoRepository, times(1)).deleteProductoById(1);
    }
}
