package com.example.VitaLife10.Service;

import com.example.VitaLife10.Metodos.HabitoUsuarioRequest;
import com.example.VitaLife10.Repository.HabitoUsuarioRepository;
import com.example.VitaLife10.Repository.UsuarioRepository;
import com.example.VitaLife10.entity.HabitoUsuario;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HabitoUsuarioService {

    private final HabitoUsuarioRepository habitoUsuarioRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public HabitoUsuarioService(HabitoUsuarioRepository habitoUsuarioRepository, UsuarioRepository usuarioRepository){
        this.habitoUsuarioRepository = habitoUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public List<HabitoUsuario> verTodosHabitos(){
        return habitoUsuarioRepository.findAll();
    }

    public HabitoUsuario crearHabitoUsuario(HabitoUsuarioRequest request) {
        // Busca al usuario por nombre de usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(request.getNombreUsuario());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Crea un nuevo hábito de usuario y asigna el usuario
            HabitoUsuario habitoUsuario = new HabitoUsuario();
            habitoUsuario.setNombre(request.getNombre());
            habitoUsuario.setGrupo(request.isGrupo());
            habitoUsuario.setUsuario(usuario);

            // Guarda el hábito de usuario en la base de datos
            return habitoUsuarioRepository.save(habitoUsuario);
        } else {
            throw new NoSuchElementException("No se encontró un usuario con nombre de usuario: " + request.getNombreUsuario());
        }
    }

    public List<HabitoUsuario> verificarHabitoGrupo(Boolean grupo){
        List<HabitoUsuario> valorgrupo = habitoUsuarioRepository.findByGrupo(true);
        if (!valorgrupo.isEmpty()){
            return valorgrupo;
        }
        return null;
    }

    public Usuario sumarPuntosHabitosUsuario(String nombreUsuario, String nombreHabito) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(nombreUsuario);
        Optional<HabitoUsuario> habitoUsuarioOptional = habitoUsuarioRepository.findByNombre(nombreHabito);

        if (usuarioOptional.isPresent() && habitoUsuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            HabitoUsuario habitoUsuario = habitoUsuarioOptional.get();

            // Verificar si el hábito ya se ha completado
            if (!habitoUsuario.isRealizado()) {
                int puntosAsumar = 3;
                // Actualizar los puntos del usuario y marcar el hábito como realizado
                usuario.setPuntos(usuario.getPuntos() + puntosAsumar);
                habitoUsuario.setRealizado(true);

                // Guardar los cambios en las entidades
                usuarioRepository.save(usuario);
                habitoUsuarioRepository.save(habitoUsuario);

                return usuario;
            } else {
                // El hábito ya se ha completado, puedes manejar esto como desees
                throw new IllegalStateException("El hábito ya se ha completado y no se pueden agregar más puntos.");
            }
        } else {
            throw new NoSuchElementException("No se encontró el usuario o el hábito en la base de datos.");
        }
    }



}
