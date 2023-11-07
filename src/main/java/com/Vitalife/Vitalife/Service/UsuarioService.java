package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        List<Usuario> existingUsersByEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
        List<Usuario> existingUsersByNombreUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

        if (!existingUsersByEmail.isEmpty()) {
            throw new IllegalStateException("El correo ya está en uso.");
        }

        if (!existingUsersByNombreUsuario.isEmpty()) {
            throw new IllegalStateException("El nombre de usuario ya está en uso.");
        }

        if (usuario.getContrasena() != null && usuario.getContrasena().length() > 8 ) {
            throw new IllegalStateException("La contraseña debe tener entre menos de 8 caracteres.");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String usuario, String contrasena){
        List<Usuario> existingUsersByNombreUsuario = usuarioRepository.findByUsuario(usuario);

        if (!existingUsersByNombreUsuario.isEmpty()) {
            Usuario usuarioxd = existingUsersByNombreUsuario.get(0);
            if(usuarioxd.getContrasena().equals(contrasena)){
                return usuarioxd;
            }else{
                throw new IllegalStateException("contraseña erronea");
            }

        }else {
            throw new IllegalStateException("Correo y contraseña erronea");
        }
    }

    public Usuario modificarContrasena(Long id ,String contrasena) {
        Optional<Usuario> modificarcontr = usuarioRepository.findById(id);

        if (!modificarcontr.isEmpty()) {
            if(contrasena.length() > 8){
                throw new IllegalArgumentException("La contraseña no cumple los requisitos");
            }
            Usuario contr = modificarcontr.get();
            contr.setContrasena(contrasena);
            return usuarioRepository.save(contr);
        } else {
            throw new NoResultException("Usuario no encontrado por ID: " + id);
        }
    }

    public UsuarioDTO verPerfil(Long id){
        Optional<Usuario> perfil = usuarioRepository.findById(id);

        if(perfil.isPresent()){
            UsuarioDTO usuario = new UsuarioDTO().toDTO(perfil.get());
            return usuario;
        }else {
            throw new NoResultException("Usuario no encontrado por ID: " + id);
        }
    }

    public Usuario verPuntos(Long id){
        Optional<Usuario> puntos = usuarioRepository.findById(id);

        if (puntos.isPresent()){
            Usuario punto = puntos.get();
            return punto;
        }else {
            throw new NoResultException("Usuario no encontrado por ID: " + id);
        }
    }
}