package com.example.VitaLife10.Service;

import com.example.VitaLife10.Repository.TituloRepository;
import com.example.VitaLife10.Repository.UsuarioRepository;
import com.example.VitaLife10.entity.Titulo;
import com.example.VitaLife10.entity.Usuario;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TituloService {

    private final TituloRepository tituloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public TituloService(TituloRepository tituloRepository) {
        this.tituloRepository = tituloRepository;
    }

    public List<Titulo> obtenerTodosLosTitulos() {
        return tituloRepository.findAll();
    }

    public Titulo guardarTitulo(Titulo titulo){
        return tituloRepository.save(titulo);
    }

    public Titulo obtenerTituloPorId(Long id){
        Optional<Titulo> tituloOptional = tituloRepository.findById(id);
        if (tituloOptional.isPresent()) {
            Titulo titulo = tituloOptional.get();
            return titulo;
        }
        return null;
    }

    public void asignarTituloAUsuarios(Long tituloId, List<Long> usuarioIds) {
        Optional<Titulo> optionalTitulo = tituloRepository.findById(tituloId);
        if (optionalTitulo.isPresent()) {
            Titulo titulo = optionalTitulo.get();

            // Obtener todos los usuarios por sus IDs
            List<Usuario> usuarios = usuarioRepository.findAllById(usuarioIds);
            if (usuarios.size() != usuarioIds.size()) {
                throw new NoSuchElementException("Al menos uno de los usuarios no se encontró en la base de datos.");
            }
            // Asignar el título a cada usuario y viceversa
            for (Usuario usuario : usuarios) {
                usuario.getTitulos().add(titulo);
                titulo.getUsuarios().add(usuario);
            }

            // Guardar los cambios en el repositorio
            tituloRepository.save(titulo);
            usuarioRepository.saveAll(usuarios);
        }
        else{
            throw new NoSuchElementException("El título con ID " + tituloId + " no se encontró en la base de datos.");
        }
    }
}
