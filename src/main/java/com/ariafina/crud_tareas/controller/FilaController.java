package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class FilaController {

    private final TareaService tareaService;

    public FilaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Integer id) {
        Optional<Tarea> tarea = tareaService.obtenerPorId(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarea crearTarea(@Valid @RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Integer id, @Valid @RequestBody Tarea tarea) {
        if (tareaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarea.setId(id);
        return ResponseEntity.ok(tareaService.guardar(tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Integer id) {
        if (tareaService.obtenerPorId(id).isPresent()) {
            tareaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
