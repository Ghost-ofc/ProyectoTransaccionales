package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;
    @BeforeEach
    void setUp() {
        // Configura objetos mock
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);


    }

    @Test
    void registrarUsuario() {
        Usuario nuevoUsuario = new Usuario(2L, "nuevoUsuario", "gegdfgd", "nuevo@ejemplo.com", 50, new ArrayList<>());

        // Configura el comportamiento esperado al guardar el nuevo usuario
        when(usuarioRepository.save(nuevoUsuario)).thenReturn(nuevoUsuario);

        // Llama al método registrarUsuario y verifica que no lance una excepción
        assertDoesNotThrow(() -> usuarioService.registrarUsuario(nuevoUsuario));

        // Verifica que el método save del repositorio se llamó una vez
        verify(usuarioRepository, times(1)).save(nuevoUsuario);
    }

    @Test
    void iniciarSesion() {
        // Define las credenciales de prueba
        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";

        // Simula la existencia de un usuario en la base de datos con el nombre de usuario dado
        Usuario usuarioExistente = new Usuario(1L, nombreDeUsuario, contrasena, "correo@example.com", 100, new ArrayList<>());
        when(usuarioRepository.findByUsuario(nombreDeUsuario)).thenReturn(Optional.of(usuarioExistente));

        // Llama al método iniciarSesion
        Usuario usuarioIniciadoSesion = usuarioService.iniciarSesion(nombreDeUsuario, contrasena);

        // Verifica que se haya iniciado sesión con éxito y que se haya devuelto el usuario correcto
        assertNotNull(usuarioIniciadoSesion);
        assertEquals(usuarioExistente, usuarioIniciadoSesion);

        // Verifica que el método usuarioRepository.findByUsuario se haya llamado con el nombre de usuario
        verify(usuarioRepository).findByUsuario(nombreDeUsuario);

        // Prueba con credenciales incorrectas (usuario no encontrado)
        when(usuarioRepository.findByUsuario("usuarioInexistente")).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            usuarioService.iniciarSesion("usuarioInexistente", "contrasenaIncorrecta");
        });

        // Verifica que el método usuarioRepository.findByUsuario se haya llamado con el nombre de usuario incorrecto
        verify(usuarioRepository).findByUsuario("usuarioInexistente");
    }

    @Test
    void modificarContrasena() {
        // Define las credenciales de prueba
        String nombreDeUsuario = "usuarioPrueba";
        String contrasenaActual = "efefef";
        String nuevaContrasena = "fefege";

        // Simula la existencia de un usuario en la base de datos con el nombre de usuario y la contraseña actual
        Usuario usuarioExistente = new Usuario(1L, nombreDeUsuario, contrasenaActual, "correo@example.com", 100, new ArrayList<>());
        // Simula la existencia de un usuario en la base de datos con el ID esperado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Llama al método modificarContrasena
        usuarioService.modificarContrasena(1L, nuevaContrasena);

        // Verifica que la contraseña del usuario se haya actualizado
        assertEquals(nuevaContrasena, usuarioExistente.getContrasena());

        // Verifica que el método usuarioRepository.save se haya llamado para guardar los cambios
        verify(usuarioRepository).save(usuarioExistente);

        // Prueba con una contraseña actual incorrecta
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(IllegalStateException.class, () -> {
            usuarioService.modificarContrasena(1L, "contrasenaIncorrecta");
        });

        // Verifica que no se haya llamado al método usuarioRepository.save con una contraseña incorrecta
        verify(usuarioRepository, never()).save(usuarioExistente);
    }

    @Test
    void verPerfil() {

        // Define las credenciales de prueba
        String nombreDeUsuario = "usuarioPrueba";
        String contrasenaActual = "efefef";

        // Simula la existencia de un usuario en la base de datos con el nombre de usuario y la contraseña actual
        Usuario usuarioExistente = new Usuario(1L, nombreDeUsuario, contrasenaActual, "correo@example.com", 100, new ArrayList<>());

        // Simula la existencia de un usuario en la base de datos con el ID esperado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Llama al método verPerfil
        UsuarioDTO usuarioDTO = usuarioService.verPerfil(1L);

        // Verifica que la conversión de Usuario a UsuarioDTO haya ocurrido correctamente
        assertEquals(usuarioExistente.getId_usuario(), usuarioDTO.getId_usuario());
        assertEquals(usuarioExistente.getUsuario(), usuarioDTO.getUsername());

        // Verifica que el método usuarioRepository.findById se haya llamado con el ID esperado
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void verPuntos() {
        // Define las credenciales de prueba
        String nombreDeUsuario = "usuarioPrueba";
        String contrasenaActual = "efefef";

        // Simula la existencia de un usuario en la base de datos con el nombre de usuario y la contraseña actual
        Usuario usuarioExistente = new Usuario(1L, nombreDeUsuario, contrasenaActual, "correo@example.com", 100, new ArrayList<>());

        // Simula la existencia de un usuario en la base de datos con el ID esperado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Llama al método verPuntos y verifica que no lance una excepción
        assertThrows(NoResultException.class, () -> usuarioService.verPuntos(2L));

        // Llama al método verPuntos
        int puntosObtenidos = usuarioService.verPuntos(1L);

        // Verifica que los puntos obtenidos sean iguales a 100
        assertEquals(100, puntosObtenidos);

        // Verifica que el método findById haya sido llamado una vez con el ID esperado
        verify(usuarioRepository).findById(1L);
    }
}