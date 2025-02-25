package com.ariafina.crud_tareas.repository;

import com.ariafina.crud_tareas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Object> findByNombre(String nombre);
}

