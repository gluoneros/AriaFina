package com.ariafina.crud_tareas.service;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ariafina.crud_tareas.repository.TareaRepository.*;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    public static Optional<Tarea> obtenerPorId(Integer id) {
        Optional<Tarea> byNombre = TareaRepository.findByNombre(String.valueOf(id));
        return byNombre;
    }

    public Optional<Tarea> obtenerPorNombre(String nombre) {
        return tareaRepository.findByNombre(nombre);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public void eliminar(Integer id) {
        tareaRepository.deleteById(id);
    }
}
