package com.example.tienda.service;

import com.example.tienda.model.Tienda;
import com.example.tienda.repository.TiendaRepository;
import com.example.tienda.service.impl.TiendaServiceImpl;
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
class TiendaServiceImplTest {

    @Mock private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaServiceImpl tiendaService;

    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(1, "Tienda Central", "Av. Principal", null, "Política general", 5);
    }

    @Test
    @DisplayName("getAllTiendas - retorna lista de tiendas")
    void getAllTiendas_retornaLista() {
        when(tiendaRepository.findAll()).thenReturn(List.of(tienda));

        List<Tienda> resultado = tiendaService.getAllTiendas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getTiendaById - retorna tienda cuando existe")
    void getTiendaById_existente() {
        when(tiendaRepository.buscarPorId(1)).thenReturn(List.of(tienda));

        Tienda resultado = tiendaService.getTiendaById(1);

        assertNotNull(resultado);
        assertEquals("Tienda Central", resultado.getNombre_tienda());
    }

    @Test
    @DisplayName("getTiendaById - retorna null cuando no existe")
    void getTiendaById_noExistente() {
        when(tiendaRepository.buscarPorId(99)).thenReturn(List.of());

        Tienda resultado = tiendaService.getTiendaById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda y retorna la tienda")
    void save_guardaTienda() {
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        Tienda resultado = tiendaService.save(tienda);

        assertNotNull(resultado);
        verify(tiendaRepository, times(1)).save(tienda);
    }

    @Test
    @DisplayName("updateTienda - actualiza cuando existe")
    void updateTienda_existente() {
        Tienda nueva = new Tienda(1, "Tienda Norte", "Calle 45", null, "Nueva política", 5);
        when(tiendaRepository.buscarPorId(1)).thenReturn(List.of(tienda));
        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(inv -> inv.getArgument(0));

        Tienda resultado = tiendaService.updateTienda(1, nueva);

        assertNotNull(resultado);
        assertEquals("Tienda Norte", resultado.getNombre_tienda());
    }

    @Test
    @DisplayName("updateTienda - retorna null cuando no existe")
    void updateTienda_noExistente() {
        when(tiendaRepository.buscarPorId(99)).thenReturn(List.of());

        Tienda resultado = tiendaService.updateTienda(99, tienda);

        assertNull(resultado);
        verify(tiendaRepository, never()).save(any());
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(tiendaRepository).deleteTiendaById(1);

        tiendaService.delete(1);

        verify(tiendaRepository, times(1)).deleteTiendaById(1);
    }
}
