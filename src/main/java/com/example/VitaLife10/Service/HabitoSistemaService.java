package com.example.VitaLife10.Service;

import com.example.VitaLife10.Repository.HabitoSistemaRepository;
import com.example.VitaLife10.Repository.UsuarioRepository;
import com.example.VitaLife10.entity.HabitoSistema;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HabitoSistemaService {

    private final HabitoSistemaRepository habitoSistemaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public HabitoSistemaService(HabitoSistemaRepository habitoSistemaRepository, UsuarioRepository usuarioRepository){
        this.habitoSistemaRepository = habitoSistemaRepository;
        this.usuarioRepository = usuarioRepository;
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

    public Usuario sumarPuntosYAgregarHabito(String nombreUsuario, String nombreHabito) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(nombreUsuario);
        Optional<HabitoSistema> habitoSistemaOptional = habitoSistemaRepository.findByNombre(nombreHabito);

        if (usuarioOptional.isPresent() && habitoSistemaOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            HabitoSistema habitoSistema = habitoSistemaOptional.get();

            if (!habitoSistema.isRealizado()) {
                int puntosAsumar = 3;

                usuario.setPuntos(usuario.getPuntos() + puntosAsumar);
                habitoSistema.setRealizado(true);

                // Agregar el hábito del sistema al usuario
                usuario.getHabitosSistema().add(habitoSistema);

                usuarioRepository.save(usuario);
                habitoSistemaRepository.save(habitoSistema);

                return usuario;
            } else {
                throw new IllegalStateException("El hábito ya se ha completado y no se pueden agregar más puntos.");
            }
        } else {
            throw new NoSuchElementException("No se encontró el usuario o el hábito en la base de datos.");
        }
    }


    public Usuario agregarPuntosyHabitoInfo(String nombreUsuario, String nombreHabito){
        Optional<Usuario> user = usuarioRepository.findByNombreUsuario(nombreUsuario);
        Optional<HabitoSistema> habitoSistemaOptional = habitoSistemaRepository.findByNombre(nombreHabito);

        if (user.isPresent() && habitoSistemaOptional.isPresent()) {
            Usuario usuario = user.get();
            HabitoSistema habitoSistema = habitoSistemaOptional.get();

            if (!habitoSistema.isRealizado()) {
                if(habitoSistema.isInfo()){
                    int puntosAsumar = 3;

                    usuario.setPuntos(usuario.getPuntos() + puntosAsumar);
                    habitoSistema.setRealizado(true);

                    // Agregar el hábito del sistema al usuario
                    usuario.getHabitosSistema().add(habitoSistema);

                    usuarioRepository.save(usuario);
                    habitoSistemaRepository.save(habitoSistema);

                    return usuario;

                }else {
                    throw new IllegalStateException("Habito De Sistema pero no de informacion");
                }

            } else {
                throw new IllegalStateException("El hábito ya se ha completado y no se pueden agregar más puntos.");
            }
        } else {
            throw new NoSuchElementException("No se encontró el usuario o el hábito en la base de datos.");
        }
    }

}
