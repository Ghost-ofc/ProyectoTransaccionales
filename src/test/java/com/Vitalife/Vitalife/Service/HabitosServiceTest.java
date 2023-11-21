package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.Repository.HabitosRepository;
import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Titulos;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitosServiceTest {

    private HabitosService habitosService;
    private HabitosRepository habitosRepository;
    private TitulosRepository titulosRepository;
    @BeforeEach
    void setUp() {
        habitosRepository = mock(HabitosRepository.class);
        titulosRepository = mock(TitulosRepository.class);
        habitosService = new HabitosService(habitosRepository, titulosRepository);
    }

    @Test
    void agregarHabito() {
        // Crea una instancia de Titulos sin argumentos
        Titulos titulo = new Titulos();
        titulo.setId_titulo(1L);
        titulo.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuario = new Usuario(1L, nombreDeUsuario, contrasena, "correo@example.com", 100, new ArrayList<>());

        Habitos habito = new Habitos(1L, "Hábito de prueba", false, 10, "Tipo de Hábito", titulo, usuario);

        when(habitosRepository.save(habito)).thenReturn(habito);

        // Llama al método agregarHabito
        Habitos habitoAgregado = habitosService.agregarHabito(habito);

        assertEquals(habito, habitoAgregado);

        verify(habitosRepository, times(1)).save(habito);

        when(habitosRepository.save(habito)).thenThrow(new RuntimeException("Error al guardar el hábito"));

        assertThrows(RuntimeException.class, () -> {
            habitosService.agregarHabito(habito);
        });
    }

    @Test
    void vetTodosHabitos() {
        // Crea una instancia de Titulos sin argumentos
        Titulos titulo = new Titulos();
        titulo.setId_titulo(1L);
        titulo.setNombretitulo("Título de prueba");

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuario = new Usuario(1L, nombreDeUsuario, contrasena, "correo@example.com", 100, new ArrayList<>());

        Habitos habito = new Habitos(1L, "Hábito de prueba", false, 10, "Tipo de Hábito", titulo, usuario);
        when(habitosRepository.findAll()).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> habitosService.vetTodosHabitos());

        verify(habitosRepository, times(1)).findAll();

    }

    @Test
    void verHabitos() {
        // Define el ID del hábito que deseas buscar
        Long idHabito = 1L;

        // Simula que el hábito no se encuentra en la base de datos
        when(habitosRepository.findById(idHabito)).thenReturn(Optional.empty());

        // Prueba que se lance la excepción NoResultException
        assertThrows(NoResultException.class, () -> {
            habitosService.verHabitos(idHabito);
        });

        // Verifica que el método findById del repositorio se haya llamado
        verify(habitosRepository).findById(idHabito);
    }

    @Test
    void verHabitosPersonalizados() {
        // Simula que no hay hábitos en la base de datos
        when(habitosRepository.findAll()).thenReturn(Collections.emptyList());

        // Prueba que se lance la excepción NoResultException
        assertThrows(NoResultException.class, () -> {
            habitosService.verHabitosPersonalizados();
        });

        // Verifica que el método findAll del repositorio se haya llamado
        verify(habitosRepository).findAll();
    }

    @Test
    void verHabitosSistema() {
        // Simula que no hay hábitos en la base de datos
        when(habitosRepository.findAll()).thenReturn(Collections.emptyList());

        // Prueba que se lance la excepción NoResultException
        assertThrows(NoResultException.class, () -> {
            habitosService.verHabitosSistema();
        });

        // Verifica que el método findAll del repositorio se haya llamado
        verify(habitosRepository).findAll();
    }

    @Test
    void verHabitosGrupo() {
        // Simula que no hay hábitos en la base de datos
        when(habitosRepository.findAll()).thenReturn(Collections.emptyList());

        // Prueba que se lance la excepción NoResultException
        assertThrows(NoResultException.class, () -> {
            habitosService.verHabitosGrupo();
        });

        // Verifica que el método findAll del repositorio se haya llamado
        verify(habitosRepository).findAll();
    }

    @Test
    void completarHabitos() {
        // Simula que el hábito existe y no está completado
        Titulos titulo = new Titulos();
        titulo.setId_titulo(1L);
        titulo.setNombretitulo("Título de prueba");
        titulo.setProgresotitulo(100);
        titulo.setPuntosrequeridostitulo(1000);

        String nombreDeUsuario = "usuarioPrueba";
        String contrasena = "contrasenaPrueba";
        Usuario usuario = new Usuario(1L, nombreDeUsuario, contrasena, "correo@example.com", 100, new ArrayList<>());

        Habitos habito = new Habitos(1L, "Hábito de prueba", false, 10, "Tipo de Hábito", titulo, usuario);

        // Configura el comportamiento esperado para el repositorio
        when(habitosRepository.findById(1L)).thenReturn(Optional.of(habito));
        when(titulosRepository.save(titulo)).thenReturn(titulo);
        when(habitosRepository.save(habito)).thenReturn(habito);

        // Llama al método completarHabitos y verifica las excepciones
        assertThrows(NoResultException.class, () -> {
            habitosService.completarHabitos(2L); // Intenta completar un hábito que no existe
        });


        // Verifica que el título se haya actualizado con los puntos
        Habitos habitoCompletado = habitosService.completarHabitos(1L);
        assertEquals(110, titulo.getProgresotitulo()); // Debe aumentar en 10

        // Verifica que el título se haya guardado
        verify(titulosRepository, times(1)).save(titulo);

        // Verifica que el hábito se haya guardado
        verify(habitosRepository).save(habito);
    }
}