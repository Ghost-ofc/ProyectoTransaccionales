package com.example.VitaLife10.Repository;

import com.example.VitaLife10.entity.HabitoSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitoSistemaRepository extends JpaRepository<HabitoSistema, Long> {


    Optional<HabitoSistema> findByNombre(String nombre);
}
