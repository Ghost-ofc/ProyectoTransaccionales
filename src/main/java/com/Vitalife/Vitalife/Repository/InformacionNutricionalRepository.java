package com.Vitalife.Vitalife.Repository;

import com.Vitalife.Vitalife.entity.InformacionNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionNutricionalRepository extends JpaRepository<InformacionNutricional, Long>{
}
