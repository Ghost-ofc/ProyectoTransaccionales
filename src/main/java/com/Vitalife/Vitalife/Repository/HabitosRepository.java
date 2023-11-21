package com.Vitalife.Vitalife.Repository;

import com.Vitalife.Vitalife.entity.Habitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitosRepository extends JpaRepository<Habitos, Long> {
}
