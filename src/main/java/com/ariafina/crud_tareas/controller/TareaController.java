package com.ariafina.crud_tareas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
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
    public ResponseEntity<?> obtenerTareaPorId(@PathVariable Integer id) {
        return tareaService.obtenerPorId(id)
                .map(tarea -> ResponseEntity.ok(tarea))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body((Tarea) Map.of("error", "No se encontró la tarea con ID " + id)));
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errores);
        }
        Tarea nuevaTarea = tareaService.guardar(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Integer id, @Valid @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    // Eliminar una tarea por ID
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminar(id);
    }
}
