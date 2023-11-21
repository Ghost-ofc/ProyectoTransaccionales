package com.Vitalife.Vitalife.Repository;

import com.Vitalife.Vitalife.entity.Marcaje;
import com.Vitalife.Vitalife.entity.Titulos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcajeRepository extends JpaRepository<Marcaje, Long> {

}
