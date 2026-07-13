package com.example.empleado.service;

import com.example.empleado.dto.EmpleadoDTO;
import com.example.empleado.dto.TiendaResponse;
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

    @Mock
    private EmpleadoRepository empleadoRepository;
    @Mock
    private TiendaClient tiendaClient;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;
    private TiendaResponse tiendaResponse;

    @BeforeEach
    void setUp() {
        empleado = new Empleado(1, "Ana", "López García", 50000, 2, 3);
        tiendaResponse = new TiendaResponse(3, "Tienda Central", "Av. Principal", null, "Política general");
    }

    @Test
    @DisplayName("getAllEmpleados - retorna lista de empleados enriquecidos")
    void getAllEmpleados_retornaLista() {
        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        List<EmpleadoDTO.Response> resultado = empleadoService.getAllEmpleados();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("getEmpleadoById - retorna empleado cuando existe")
    void getEmpleadoById_existente_retornaEmpleado() {
        when(empleadoRepository.buscarPorId(1)).thenReturn(List.of(empleado));
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        EmpleadoDTO.Response resultado = empleadoService.getEmpleadoById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_empleado());
    }

    @Test
    @DisplayName("getEmpleadoById - retorna null cuando no existe")
    void getEmpleadoById_noExistente_retornaNull() {
        when(empleadoRepository.buscarPorId(99)).thenReturn(List.of());

        EmpleadoDTO.Response resultado = empleadoService.getEmpleadoById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda empleado y retorna respuesta enriquecida")
    void save_guardaEmpleado() {
        EmpleadoDTO.Request request = new EmpleadoDTO.Request(0, "Ana", "López García", 50000, 3);
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);
        when(tiendaClient.getTiendaById(3)).thenReturn(tiendaResponse);

        EmpleadoDTO.Response resultado = empleadoService.save(request);

        assertNotNull(resultado);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(empleadoRepository).deleteEmpleadoById(1);

        empleadoService.delete(1);

        verify(empleadoRepository, times(1)).deleteEmpleadoById(1);
    }
}
