package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService TareaService;

    @GetMapping
    public List<Tarea> listarTareas() {
        return TareaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTodas(@PathVariable Integer id) {
        Optional<Tarea> tarea = com.ariafina.crud_tareas.service.TareaService.obtenerPorId(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return TareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Integer id, @RequestBody Tarea tarea) {
        if (!TareaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarea.setId(id);
        return ResponseEntity.ok(TareaService.guardar(tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!TareaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
