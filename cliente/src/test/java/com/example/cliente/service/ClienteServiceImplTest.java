package com.example.cliente.service;

import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepository;
import com.example.cliente.service.impl.ClienteServiceImpl;
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
class ClienteServiceImplTest {

    @Mock private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Juan", "Pérez", "juan@mail.com");
    }

    @Test
    @DisplayName("getAllClientes - retorna lista de clientes")
    void getAllClientes_retornaLista() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.getAllClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("getClienteById - retorna cliente cuando existe")
    void getClienteById_existente() {
        when(clienteRepository.buscarPorId(1)).thenReturn(List.of(cliente));

        Cliente resultado = clienteService.getClienteById(1);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    @DisplayName("getClienteById - retorna null cuando no existe")
    void getClienteById_noExistente() {
        when(clienteRepository.buscarPorId(99)).thenReturn(List.of());

        Cliente resultado = clienteService.getClienteById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda y retorna el cliente")
    void save_guardaCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    @DisplayName("updateCliente - actualiza cuando existe")
    void updateCliente_existente() {
        Cliente datosNuevos = new Cliente(1, "Carlos", "López", "carlos@mail.com");
        when(clienteRepository.buscarPorId(1)).thenReturn(List.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(inv -> inv.getArgument(0));

        Cliente resultado = clienteService.updateCliente(1, datosNuevos);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
    }

    @Test
    @DisplayName("updateCliente - retorna null cuando no existe")
    void updateCliente_noExistente() {
        when(clienteRepository.buscarPorId(99)).thenReturn(List.of());

        Cliente resultado = clienteService.updateCliente(99, cliente);

        assertNull(resultado);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(clienteRepository).deleteClienteById(1);

        clienteService.delete(1);

        verify(clienteRepository, times(1)).deleteClienteById(1);
    }
}
