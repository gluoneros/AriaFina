package com.ariafina.crud_tareas.repository;

import com.ariafina.crud_tareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    Optional<Tarea> findByNombre(String nombre);
}
