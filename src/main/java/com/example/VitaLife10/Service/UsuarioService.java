package com.example.VitaLife10.Service;

import com.example.VitaLife10.Repository.UsuarioRepository;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorUsername(String nombreUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario;
        }
        return null;
    }


    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario;
        }
        return null;
    }

    public Usuario guardarUsuario(Usuario usuario) {
        Optional<Usuario> existingUserByEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
        Optional<Usuario> existingUserByNombreUsuario = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalStateException("El correo ya está en uso.");
        }

        if (existingUserByNombreUsuario.isPresent()) {
            throw new IllegalStateException("El nombre de usuario ya está en uso.");
        }

        return usuarioRepository.save(usuario);
    }

    public boolean eliminarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Usuario verPuntosPorUsername(String nombreUsuario){
        Optional<Usuario> puntosUs = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (puntosUs.isPresent()){
            Usuario usuario = puntosUs.get();
            return usuario;
        }
        return null;
    }

    public Usuario verificarCredenciales(String nombreUsuario, String passwd) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Verificar si la contraseña coincide
            if (usuario.getPasswd().equals(passwd)) {
                // Las credenciales son válidas
                return usuario;
            }
        }

        // Si no se encontró el usuario o las credenciales no coinciden, devuelve null
        return null;
    }


}
