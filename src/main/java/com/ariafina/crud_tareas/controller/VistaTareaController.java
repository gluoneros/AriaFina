package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tareas")
public class VistaTareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/vista")
    public String listarTareas(Model model) {
        model.addAttribute("tareas", tareaService.obtenerTodas());
        return "tareas"; // Debe coincidir con el nombre del HTML en templates
    }

    @PostMapping("/crear")
    public String crearTarea(@RequestParam String nombre) {
        Tarea nuevaTarea = new Tarea(nombre);
        tareaService.guardar(nuevaTarea);
        return "tarea-form";
    }
}
