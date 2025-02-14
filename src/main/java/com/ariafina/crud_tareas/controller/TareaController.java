package com.ariafina.crud_tareas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//import java.util.Optional;

import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // Obtener todas las tareas
    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodas();
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTareaPorId(@PathVariable Integer id) {
        return tareaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body((Tarea) Map.of("error", "No se encontró la tarea con ID " + id)));
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea, BindingResult result) {
        ResponseEntity<?> errores = getResponseEntity(result);
        if (errores != null) return errores;
        Tarea nuevaTarea = tareaService.guardar(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarea(@PathVariable Integer id, @Valid @RequestBody Tarea tarea, BindingResult result) {
        ResponseEntity<?> errores = getResponseEntity(result);
        if (errores != null) return errores;

        try {
            Tarea tareaActualizada = tareaService.actualizar(id, tarea);
            return ResponseEntity.ok(tareaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    private ResponseEntity<?> getResponseEntity(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }


    // Eliminar una tarea por ID
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminar(id);
    }
}
