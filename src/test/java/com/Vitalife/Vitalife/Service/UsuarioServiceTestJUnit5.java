package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UsuarioServiceTestJUnit5 {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        // Configura tus objetos de prueba
        MockitoAnnotations.initMocks(this); // Esto inicializa las anotaciones @Mock
    }
    @Test
    void registrarUsuario() {

        // Preparar datos de prueba
        Usuario usuario = new Usuario();
        usuario.setUsuario("Ejemplo");
        usuario.setCorreo("ejemplo@example.com");

        // Configurar el comportamiento simulado del repositorio
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Realizar la prueba
        String resultado = usuarioService.registrarUsuario(usuario);

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void iniciarSesion() {
        // Preparar datos de prueba
        Usuario usuario = new Usuario();
        usuario.setUsuario("Ejemplo");
        usuario.setCorreo("ejemplo@example.com");
        usuario.setContrasena("contrasenaEjemplo");

        String nombreDeUsuario = "Ejemplo";
        String contrasena = "contrasenaEjemplo";

        // Configurar el comportamiento simulado del repositorio para devolver el usuario si las credenciales son correctas
        when(usuarioRepository.findByUsuario(nombreDeUsuario)).thenReturn(Optional.of(usuario));


        // Realizar la prueba
        Usuario login = usuarioService.iniciarSesion(nombreDeUsuario, contrasena);

        // Verificar que el usuario devuelto no sea nulo
        assertNotNull(login);

        // Verificar que el usuario devuelto coincida con el usuario de prueba
        assertEquals(usuario, login);
    }

    @Test
    void modificarContrasena() {
        // Preparar datos de prueba
        Long id = 1L; // ID de usuario de ejemplo
        String contrasenaAnterior = "contrasenaAnterior";
        String nuevaContrasena = "nuevaContrasena";

        // Configurar el comportamiento simulado del repositorio para devolver el usuario si las credenciales son correctas
        Usuario usuario = new Usuario();
        usuario.setId_usuario(id);
        usuario.setContrasena(contrasenaAnterior);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Realizar la prueba
        Usuario resultado = usuarioService.modificarContrasena(id, nuevaContrasena);

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);

        // Verificar que la contraseña del usuario se haya modificado
        assertEquals(nuevaContrasena, resultado.getContrasena());

        // Verificar que el método "save" del repositorio se haya llamado una vez
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void verPerfil() {
        // Preparar datos de prueba
        Long id = 1L; // ID de usuario de ejemplo

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario("UsuarioEjemplo");
        usuarioDTO.setCorreo("ejemplo@example.com");

        Usuario usuario = new Usuario();
        usuario.setId_usuario(id); // Asegúrate de configurar el ID correctamente
        usuario.setUsuario("UsuarioEjemplo");
        usuario.setCorreo("ejemplo@example.com");

        // Configurar el comportamiento simulado del repositorio para devolver el usuario por ID
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Realizar la prueba
        UsuarioDTO perfilDTO = usuarioService.verPerfil(id);

        // Verificar que el perfil devuelto no sea nulo
        assertNotNull(perfilDTO);

        // Verificar que el perfil devuelto coincida con el objeto UsuarioDTO de prueba
        assertEquals(usuarioDTO.getUsername(), perfilDTO.getUsername());
        assertEquals(usuarioDTO.getCorreo(), perfilDTO.getCorreo());
    }

    @Test
    void verPuntos() {
        // Preparar datos de prueba
        Long id = 1L; // ID de usuario de ejemplo
        int puntosEsperados = 100; // Establece los puntos esperados

        Usuario usuario = new Usuario();
        usuario.setId_usuario(id); // Asegúrate de configurar el ID correctamente
        usuario.setPuntos(puntosEsperados); // Establece los puntos del usuario

        // Configurar el comportamiento simulado del repositorio para devolver el usuario por ID
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Realizar la prueba
        int puntos = usuarioService.verPuntos(id);

        // Verificar que los puntos devueltos coincidan con los puntos esperados
        assertEquals(puntosEsperados, puntos);
    }
}