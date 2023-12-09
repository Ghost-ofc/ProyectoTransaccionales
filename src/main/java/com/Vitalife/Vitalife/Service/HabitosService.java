package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.HabitosDTO;
import com.Vitalife.Vitalife.Repository.HabitosRepository;
import com.Vitalife.Vitalife.Repository.MarcajeRepository;
import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Marcaje;
import com.Vitalife.Vitalife.entity.Titulos;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitosService {

    private final HabitosRepository habitosRepository;
    private final TitulosRepository titulosRepository;
    private final MarcajeRepository marcajeRepository;
    private final UsuarioRepository usuarioRepository;

    public HabitosService(HabitosRepository habitosRepository, TitulosRepository titulosRepository, MarcajeRepository marcajeRepository, UsuarioRepository usuarioRepository) {
        this.habitosRepository = habitosRepository;
        this.titulosRepository = titulosRepository;
        this.marcajeRepository = marcajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public String agregarHabito(Habitos habitos) {
        habitosRepository.save(habitos);
        return "Habito agregado";
    }


    public List<Habitos> vetTodosHabitos(){
        List<Habitos> verto = habitosRepository.findAll();
        return verto;
    }

    public HabitosDTO verHabitos(Long id) {
        Optional<Habitos> habit = habitosRepository.findById(id);

        if (habit.isPresent()) {
            HabitosDTO si = new HabitosDTO().toDTO(habit.get());
            return si;
        } else {
            throw new NoResultException("No se econtro el Habito por ID: " + id);
        }
    }

    public List<Habitos> verHabitosPersonalizados() {
        List<Habitos> habitos = habitosRepository.findAll();
        if (!habitos.isEmpty()) {
            List<Habitos> personalizado = habitos.stream()
                    .filter(habitos1 -> "Personalizado".equals(habitos1.getTipohabito()))
                    .collect(Collectors.toList());
            if (personalizado.isEmpty()) {
                throw new NoResultException("No se encontraron hábitos personalizados.");
            }
            return personalizado;
        }else {
            throw new NoResultException("No se econontraron Habitos");
        }
    }

    public List<Habitos> verHabitosSistema() {
        List<Habitos> habitosistema = habitosRepository.findAll();
        if (!habitosistema.isEmpty()) {
            List<Habitos> personalizado = habitosistema.stream()
                    .filter(habitos1 -> "Sistema".equals(habitos1.getTipohabito()))
                    .collect(Collectors.toList());
            if (personalizado.isEmpty()) {
                throw new NoResultException("No se encontraron hábitos de Sistema.");
            }
            return personalizado;
        }else {
            throw new NoResultException("No se econontraron Habitos");
        }
    }

    public List<Habitos> verHabitosGrupo() {
        List<Habitos> habitosgrupo = habitosRepository.findAll();
        if (!habitosgrupo.isEmpty()) {
            List<Habitos> personalizado = habitosgrupo.stream()
                    .filter(habitos1 -> "Grupo".equals(habitos1.getTipohabito()))
                    .collect(Collectors.toList());
            if (personalizado.isEmpty()) {
                throw new NoResultException("No se encontraron hábitos de Grupo.");
            }
            return personalizado;
        }else {
            throw new NoResultException("No se encontraron Habitos");
        }
    }






}
