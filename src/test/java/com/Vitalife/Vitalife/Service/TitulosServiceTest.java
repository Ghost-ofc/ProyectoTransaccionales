package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.entity.Titulos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TitulosServiceTest {

    private TitulosService titulosService;

    @Mock
    private TitulosRepository titulosRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        titulosService = new TitulosService(titulosRepository);
    }

    @Test
    void agregarTitulo() {
        // Configura un Titulos válido
        Titulos titulos = new Titulos();
        titulos.setNiveltitulo("Nivel 1");
        titulos.setObtenidotitulo(true);
        titulos.setNombretitulo("Título de prueba");
        titulos.setProgresotitulo(50);
        titulos.setPuntosrequeridostitulo(100);

        // Configura el comportamiento del repositorio para que retorne el objeto Titulos
        when(titulosRepository.save(titulos)).thenReturn(titulos);

        // Realiza la prueba y verifica que no se lance una excepción
        assertDoesNotThrow(() -> titulosService.agregarTitulo(titulos));

        // Verifica que se haya llamado al método save en el repositorio una vez
        verify(titulosRepository, times(1)).save(titulos);
    }

    @Test
    void verTitulos() {
        // Simula una lista de Titulos
        List<Titulos> titulosList = new ArrayList<>();
        Titulos titulos = new Titulos();
        titulos.setNiveltitulo("Nivel 1");
        titulos.setObtenidotitulo(true);
        titulos.setNombretitulo("Título de prueba");
        titulos.setProgresotitulo(50);
        titulos.setPuntosrequeridostitulo(100);
        titulosList.add(titulos);

        // Simula el comportamiento del repositorio
        when(titulosRepository.findAll()).thenReturn(titulosList);

        // Realiza la prueba
        Titulos resultado = titulosService.verTitulos();

        // Verifica que el resultado sea igual al primer Titulos de la lista
        assertEquals(titulos, resultado);

        assertDoesNotThrow(() -> titulosService.verTitulos());

        // Verifica que se llamó al método findAll una vez
        verify(titulosRepository, times(2)).findAll();
    }
}