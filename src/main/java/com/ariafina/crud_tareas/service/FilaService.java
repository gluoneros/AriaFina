package com.ariafina.crud_tareas.service;

import com.ariafina.crud_tareas.model.Fila;
import com.ariafina.crud_tareas.repository.FilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilaService {

    @Autowired
    private FilaRepository filaRepository;

    public List<Fila> obtenerTodas() {
        return filaRepository.findAll();
    }

    public Optional<Fila> obtenerPorId(Integer id) {
        return filaRepository.findById(id);
    }

    public List<Fila> obtenerPorUsuario(Integer usuarioId) {
        return filaRepository.findByUsuarioId(usuarioId);
    }

    public Fila guardar(Fila fila) {
        return filaRepository.save(fila);
    }

    public void eliminar(Integer id) {
        filaRepository.deleteById(id);
    }
}
