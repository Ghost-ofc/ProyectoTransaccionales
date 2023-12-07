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

    public String registrarUsuario(Usuario usuario) {
        List<Usuario> existingUsersByEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
        Optional<Usuario> existingUsersByNombreUsuario = usuarioRepository.findByUsuario(usuario.getUsername());

        if (!existingUsersByEmail.isEmpty()) {
            throw new IllegalStateException("El correo ya está en uso.");
        }

        if (!existingUsersByNombreUsuario.isEmpty()) {
            throw new IllegalStateException("El nombre de usuario ya está en uso.");
        }
        if (usuario.getUsername() == null || usuario.getUsername().length() < 4) {
            throw new IllegalStateException("El nombre de usuario debe tener al menos 3 caracteres.");
        }

        if (usuario.getContrasena() != null && usuario.getContrasena().length() <=7 ) {
            throw new IllegalStateException("La contraseña debe tener mas de 7 caracteres.");
        }

        usuarioRepository.save(usuario);
        return "{\"success\": true, \"message\": \"Usuario registrado con éxito\"}";
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

    public UsuarioDTO verPerfil(String usuario){
        Optional<Usuario> perfil = usuarioRepository.findByUsuario(usuario);

        if(perfil.isPresent()){
            UsuarioDTO usuario1 = new UsuarioDTO().toDTO(perfil.get());
            return usuario1;
        }else {
            throw new NoResultException("Usuario no encontrado por ID: " + usuario);
        }
    }

    public Integer verPuntos(Long id){
        Optional<Usuario> puntos = usuarioRepository.findById(id);

        if (puntos.isPresent()){
            Usuario punto = puntos.get();
            Integer si = punto.getPuntos();
            return si;
        }else {
            throw new NoResultException("Usuario no encontrado por ID: " + id);
        }
    }

    public UsuarioDTO verTodosPerfil(){
        List<Usuario> perfil = usuarioRepository.findAll();
        if (!perfil.isEmpty()){
            UsuarioDTO usuario = new UsuarioDTO().toDTO(perfil.get(0));
            return usuario;
        }else {
            throw new NoResultException("No se encontraron usuarios");
        }
    }
}