package com.ariafina.crud_tareas.service;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    // Obtener todas las tareas
    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    // Obtener una tarea por ID
    public Optional<Tarea> obtenerPorId(Integer id) {
        return tareaRepository.findById(id);
    }

    // Guardar una nueva tarea
    public Tarea guardar(Tarea tarea) {
        if (tareaRepository.findByNombre(tarea.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una tarea con este nombre");
        }
        return tareaRepository.save(tarea);
    }


    // Actualizar una tarea existente
    public Tarea actualizar(Integer id, Tarea nuevaTarea) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setNombre(nuevaTarea.getNombre());
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }


    // Eliminar una tarea por ID
    public void eliminar(Integer id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarea con ID " + id + " no encontrada.");
        }
    }

    // Buscar una tarea por nombre
    public Optional<Tarea> obtenerPorNombre(String nombre) {
        return tareaRepository.findByNombre(nombre);
    }
}
