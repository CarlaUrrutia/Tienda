package com.example.empleado.service;

import com.example.empleado.DTO.EmpleadoDTO;
import com.example.empleado.DTO.TiendaResponse;
import com.example.empleado.client.TiendaClient;
import com.example.empleado.model.Empleado;
import com.example.empleado.repository.EmpleadoRepository;
import com.example.empleado.service.impl.EmpleadoServiceImpl;
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
class EmpleadoServiceImplTest {

    @Mock private EmpleadoRepository empleadoRepository;
    @Mock private TiendaClient tiendaClient;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;
    private TiendaResponse tiendaResponse;

    @BeforeEach
    void setUp() {
        empleado = new Empleado(1, "Ana", "López", 50000, 2, 3);
        tiendaResponse = new TiendaResponse(3, "Tienda Central", "Av. Principal", null, "Política general");
    }

    @Test
    @DisplayName("getAllEmpleados - retorna lista enriquecida")
    void getAllEmpleados_retornaLista() {
        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        List<EmpleadoDTO.Response> resultado = empleadoService.getAllEmpleados();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("getEmpleadoById - retorna empleado cuando existe")
    void getEmpleadoById_existente() {
        when(empleadoRepository.buscarPorId(1)).thenReturn(List.of(empleado));
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        EmpleadoDTO.Response resultado = empleadoService.getEmpleadoById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_empleado());
    }

    @Test
    @DisplayName("getEmpleadoById - retorna null cuando no existe")
    void getEmpleadoById_noExistente() {
        when(empleadoRepository.buscarPorId(99)).thenReturn(List.of());

        EmpleadoDTO.Response resultado = empleadoService.getEmpleadoById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(empleadoRepository).deleteEmpleadoById(1);

        empleadoService.delete(1);

        verify(empleadoRepository, times(1)).deleteEmpleadoById(1);
    }
}
