package com.example.VitaLife10.Service;

import com.example.VitaLife10.Repository.HabitoSistemaRepository;
import com.example.VitaLife10.Repository.HabitoUsuarioRepository;
import com.example.VitaLife10.entity.HabitoSistema;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoSistemaService {

    private final HabitoSistemaRepository habitoSistemaRepository;

    @Autowired
    public HabitoSistemaService(HabitoSistemaRepository habitoSistemaRepository){
        this.habitoSistemaRepository = habitoSistemaRepository;
    }

    public HabitoSistema agregarHabitoSistema(HabitoSistema habitoSistema){
        return habitoSistemaRepository.save(habitoSistema);
    }

    public List<HabitoSistema> verTodosHabitoSistema(){
        return habitoSistemaRepository.findAll();
    }

    public Boolean eliminarHabitoPorId(Long id){
        Optional<HabitoSistema> habitoSi = habitoSistemaRepository.findById(id);
        if (habitoSi.isPresent()) {
            habitoSistemaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
