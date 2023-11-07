package com.Vitalife.Vitalife.Repository;

import com.Vitalife.Vitalife.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCorreo(String correo);
    List<Usuario> findByUsuario(String usuario);


}
