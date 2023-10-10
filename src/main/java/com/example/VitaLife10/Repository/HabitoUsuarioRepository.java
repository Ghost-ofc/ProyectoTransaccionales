package com.example.VitaLife10.Repository;

import com.example.VitaLife10.entity.HabitoUsuario;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitoUsuarioRepository extends JpaRepository<HabitoUsuario, Long> {
    List<HabitoUsuario> findByGrupo(Boolean grupo);
    Optional<HabitoUsuario> findByNombre(String nombre);
}
