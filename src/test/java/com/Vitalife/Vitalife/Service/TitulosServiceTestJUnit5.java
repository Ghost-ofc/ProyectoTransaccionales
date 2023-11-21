package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Titulos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
class TitulosServiceTestJUnit5 {

    @InjectMocks
    private TitulosService titulosService;

    @Mock
    private TitulosRepository titulosRepository;

    @BeforeEach
    void setUp() {
        // Configura tus objetos de prueba
        MockitoAnnotations.initMocks(this); // Esto inicializa las anotaciones @Mock
    }
    @Test
    void agregarTitulo() {
        Titulos titulos = new Titulos();
        titulos.setId_titulo(1L);
        titulos.setNombretitulo("Título de prueba");
        titulos.setProgresotitulo(100);
        titulos.setPuntosrequeridostitulo(1000);
        titulos.setNiveltitulo("Nivel de prueba");
        titulos.setObtenidotitulo(false);


        when(titulosRepository.save(titulos)).thenReturn(titulos);

        Titulos resultado = titulosService.agregarTitulo(titulos);

        assertEquals(titulos, resultado);
    }

    @Test
    void verTitulos() {
        // Preparar datos de prueba
        Titulos titulo = new Titulos(1L, "Título de prueba", 100, "Nivel de prueba", 1000, false, new ArrayList<>());
        List<Titulos> titulosList = new ArrayList<>();
        titulosList.add(titulo);

        // Configurar el comportamiento simulado del repositorio para devolver la lista de títulos
        when(titulosRepository.findAll()).thenReturn(titulosList);

        // Llamar al método verTitulos
        Titulos resultado = titulosService.verTitulos();

        // Verificar que el resultado no sea nulo
        assertNotNull(resultado);
        // Verificar que el título devuelto coincida con el título de prueba
        assertEquals(titulo, resultado);
    }
}