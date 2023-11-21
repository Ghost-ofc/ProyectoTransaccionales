package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.HabitosDTO;
import com.Vitalife.Vitalife.DTO.TituloDTO;
import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Repository.HabitosRepository;
import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Titulos;
import com.Vitalife.Vitalife.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class HabitosServiceTestJUnit5 {

    @InjectMocks
    private HabitosService habitosService;

    @Mock
    private HabitosRepository habitosRepository;
    @Mock
    private TitulosRepository titulosRepository;
    @BeforeEach
    void setUp() {
        // Configura tus objetos de prueba
        MockitoAnnotations.initMocks(this); // Esto inicializa las anotaciones @Mock
    }
    @Test
    void agregarHabito() {
        // Crear un objeto Habitos para usar en la prueba
        Habitos habitos = new Habitos(1L, "Hábito de prueba", false, 10, "Tipo de Hábito", new Titulos(), new Usuario());

        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.save(habitos)).thenReturn(habitos);

        // Llamar al método agregarHabito
        Habitos resultado = habitosService.agregarHabito(habitos);

        // Verificar que el resultado sea igual al hábito original
        assertEquals(habitos, resultado);
    }

    @Test
    void vetTodosHabitos() {
        // Preparar datos de prueba
        Habitos habitos = new Habitos(1L, "Hábito de prueba", false, 10, "Tipo de Hábito", new Titulos(), new Usuario());

        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.findAll()).thenReturn(new ArrayList<>());

        // Llamar al método vetTodosHabitos
        List<Habitos> resultado = habitosService.vetTodosHabitos();

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void verHabitos() {
        // Crea una instancia de Titulos sin argumentos
        TituloDTO titulos = new TituloDTO();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        UsuarioDTO usuarios = new UsuarioDTO(1L, nombreDeUsuario,"fe", 100, new ArrayList<>());

        HabitosDTO habitos = new HabitosDTO(1L, "Hábito de prueba", false, 10, "Tipo de Hábito",usuarios, titulos);

        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.findById(1L)).thenReturn(Optional.of(new Habitos()));

        // Llamar al método verHabitos
        HabitosDTO resultado = habitosService.verHabitos(1L);

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void verHabitosPersonalizados() {
        // Crea una instancia de Titulos sin argumentos
        Titulos titulos = new Titulos();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuarios = new Usuario(1L, nombreDeUsuario, contrasena, "fe", 100, new ArrayList<>());

        Habitos habitos = new Habitos(1L, "Hábito de prueba", false, 10, "Personalizado", titulos, usuarios);
        ArrayList<Habitos> habitosArrayList = new ArrayList<>();
        habitosArrayList.add(habitos);
        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.findAll()).thenReturn(habitosArrayList);

        // Llamar al método verHabitos
        List<Habitos> resultado = habitosService.verHabitosPersonalizados();

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void verHabitosSistema() {
        // Crea una instancia de Titulos sin argumentos
        Titulos titulos = new Titulos();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuarios = new Usuario(1L, nombreDeUsuario, contrasena, "fe", 100, new ArrayList<>());

        Habitos habitos = new Habitos(1L, "Hábito de prueba", false, 10, "Sistema", titulos, usuarios);
        ArrayList<Habitos> habitosArrayList = new ArrayList<>();
        habitosArrayList.add(habitos);
        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.findAll()).thenReturn(habitosArrayList);

        // Llamar al método verHabitos
        List<Habitos> resultado = habitosService.verHabitosSistema();

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void verHabitosGrupo() {
        // Crea una instancia de Titulos sin argumentos
        Titulos titulos = new Titulos();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuarios = new Usuario(1L, nombreDeUsuario, contrasena, "fe", 100, new ArrayList<>());

        Habitos habitos = new Habitos(1L, "Hábito de prueba", false, 10, "Grupo", titulos, usuarios);
        ArrayList<Habitos> habitosArrayList = new ArrayList<>();
        habitosArrayList.add(habitos);
        // Configurar el comportamiento simulado del repositorio para devolver el hábito
        when(habitosRepository.findAll()).thenReturn(habitosArrayList);

        // Llamar al método verHabitos
        List<Habitos> resultado = habitosService.verHabitosGrupo();

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    void completarHabitos() {
        // Preparar datos de prueba
        // Crea una instancia de Titulos sin argumentos
        Titulos titulos = new Titulos();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");
        titulos.setProgresotitulo(100);
        titulos.setPuntosrequeridostitulo(1000);

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuarios = new Usuario(1L, nombreDeUsuario, contrasena, "fe", 100, new ArrayList<>());

        Habitos habito = new Habitos(1L, "Hábito de prueba", false, 10, "Grupo", titulos, usuarios);
        Long idHabito = 1L;

        when(habitosRepository.findById(1L)).thenReturn(Optional.of(habito));
        when(titulosRepository.save(titulos)).thenReturn(titulos);
        when(habitosRepository.save(habito)).thenReturn(habito);

        // Llamar al método completarHabitos
        Habitos resultado = habitosService.completarHabitos(idHabito);

        // Verificar que el hábito está marcado como completado
        assertTrue(resultado.isCompletadoHabito());
    }
}