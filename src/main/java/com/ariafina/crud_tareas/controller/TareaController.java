package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // Obtener todas las tareas
    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodas();
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public Optional<Tarea> obtenerTareaPorId(@PathVariable Integer id) {
        return tareaService.obtenerPorId(id);
    }

    // Crear una nueva tarea
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Integer id, @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    // Eliminar una tarea por ID
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminar(id);
    }
}
