package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Titulos;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitulosService {

    private final TitulosRepository titulosRepository;

    public TitulosService(TitulosRepository titulosRepository){
        this.titulosRepository = titulosRepository;
    }

    public Titulos agregarTitulo(Titulos titulos) {
        if (titulos.getNiveltitulo() != null && titulos.getObtenidotitulo() != null &&
                titulos.getNombretitulo() != null && titulos.getProgresotitulo() != null && titulos.getPuntosrequeridostitulo() != null) {
            return titulosRepository.save(titulos);
        } else {
            throw new IllegalArgumentException("Faltan parámetros requeridos para agregar un Titulo.");
        }
    }

    public Titulos verTitulos(){
        List<Titulos> titu = titulosRepository.findAll();
        if (!titu.isEmpty()) {
            Titulos tituxd = titu.get(0);
            return tituxd;
        }else {
            throw new NoResultException("No se econontraron Habitos");
        }
    }
}
