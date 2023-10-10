package com.example.VitaLife10.Repository;

import com.example.VitaLife10.entity.HabitoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitoUsuarioRepository extends JpaRepository<HabitoUsuario, Long> {
    List<HabitoUsuario> findByGrupo(Boolean grupo);
}
