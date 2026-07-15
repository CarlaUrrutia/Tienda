package com.example.rol.service;

import com.example.rol.model.Rol;
import com.example.rol.repository.RolRepository;
import com.example.rol.service.impl.RolServiceImpl;
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
class RolServiceImplTest {

    @Mock private RolRepository rolRepository;

    @InjectMocks
    private RolServiceImpl rolService;

    private Rol rol;

    @BeforeEach
    void setUp() {
        rol = new Rol(1, "Administrador");
    }

    @Test
    @DisplayName("getAllRoles - retorna lista de roles")
    void getAllRoles_retornaLista() {
        when(rolRepository.findAll()).thenReturn(List.of(rol));

        List<Rol> resultado = rolService.getAllRoles();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Administrador", resultado.get(0).getNombre_rol());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("getRolById - retorna rol cuando existe")
    void getRolById_existente() {
        when(rolRepository.buscarPorId(1)).thenReturn(List.of(rol));

        Rol resultado = rolService.getRolById(1);

        assertNotNull(resultado);
        assertEquals("Administrador", resultado.getNombre_rol());
    }

    @Test
    @DisplayName("getRolById - retorna null cuando no existe")
    void getRolById_noExistente() {
        when(rolRepository.buscarPorId(99)).thenReturn(List.of());

        Rol resultado = rolService.getRolById(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("save - guarda y retorna el rol")
    void save_guardaRol() {
        when(rolRepository.save(rol)).thenReturn(rol);

        Rol resultado = rolService.save(rol);

        assertNotNull(resultado);
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    @DisplayName("delete - llama al repositorio para eliminar")
    void delete_llamaRepositorio() {
        doNothing().when(rolRepository).deleteRolById(1);

        rolService.delete(1);

        verify(rolRepository, times(1)).deleteRolById(1);
    }
}
