package com.ariafina.crud_tareas.repository;

import com.ariafina.crud_tareas.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Integer> {
    List<Fila> findByUsuarioId(Integer usuarioId);
}
