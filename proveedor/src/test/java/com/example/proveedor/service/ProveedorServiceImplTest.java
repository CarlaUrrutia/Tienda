package com.example.proveedor.service;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepository;
import com.example.proveedor.service.impl.ProveedorServiceImpl;
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
class ProveedorServiceImplTest {

    @Mock private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor(1, "Proveedor SA", "contacto@proveedor.com");
    }

    @Test
    @DisplayName("getAllProveedores - retorna lista")
    void getAllProveedores_retornaLista() {
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Proveedor> resultado = proveedorService.getAllProveedores();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getProveedorById - retorna proveedor cuando existe")
    void getProveedorById_existente() {
        when(proveedorRepository.buscarPorId(1)).thenReturn(List.of(proveedor));

        Proveedor resultado = proveedorService.getProveedorById(1);

        assertNotNull(resultado);
        assertEquals("Proveedor SA", resultado.getNombre());
    }

    @Test
    @DisplayName("getProveedorById - retorna null cuando no existe")
    void getProveedorById_noExistente() {
        when(proveedorRepository.buscarPorId(99)).thenReturn(List.of());

        Proveedor resultado = proveedorService.getProveedorById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda y retorna el proveedor")
    void save_guardaProveedor() {
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor resultado = proveedorService.save(proveedor);

        assertNotNull(resultado);
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    @DisplayName("updateProveedor - actualiza cuando existe")
    void updateProveedor_existente() {
        Proveedor nuevo = new Proveedor(1, "Nuevo Proveedor", "nuevo@mail.com");
        when(proveedorRepository.buscarPorId(1)).thenReturn(List.of(proveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenAnswer(inv -> inv.getArgument(0));

        Proveedor resultado = proveedorService.updateProveedor(1, nuevo);

        assertNotNull(resultado);
        assertEquals("Nuevo Proveedor", resultado.getNombre());
    }

    @Test
    @DisplayName("updateProveedor - retorna null cuando no existe")
    void updateProveedor_noExistente() {
        when(proveedorRepository.buscarPorId(99)).thenReturn(List.of());

        Proveedor resultado = proveedorService.updateProveedor(99, proveedor);

        assertNull(resultado);
        verify(proveedorRepository, never()).save(any());
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(proveedorRepository).deleteProveedorById(1);

        proveedorService.delete(1);

        verify(proveedorRepository, times(1)).deleteProveedorById(1);
    }
}
