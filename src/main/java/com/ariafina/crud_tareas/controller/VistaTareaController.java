package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Dependencia;
import com.ariafina.crud_tareas.model.Perfil;
import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.model.Usuario;
import com.ariafina.crud_tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tareas")
public class VistaTareaController {

    @Autowired
    private TareaService tareaService;

    // Listar tareas
    @GetMapping
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodas();
        model.addAttribute("tareas", tareas);
        return "tareas";
    }

    // Mostrar formulario para crear o editar tarea
    @GetMapping({"/nuevo", "/editar/{id}"})
    public String mostrarFormulario(@PathVariable(required = false) Integer id, Model model) {
        Tarea tarea;
        if (id != null) {
            tarea = tareaService.obtenerPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        } else {
            tarea = new Tarea();
        }
        model.addAttribute("tarea", tarea);
        model.addAttribute("dependencias", Dependencia.values()); // Para el dropdown
        model.addAttribute("perfiles", Perfil.values());         // Para el dropdown
        return "tarea-form"; // Nombre de la plantilla del formulario
    }

    // Guardar o actualizar tareas
    @PostMapping("/guardar")
    public String guardarTarea(@Valid @ModelAttribute("tarea") Tarea tarea,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dependencias", Dependencia.values());
            model.addAttribute("perfiles", Perfil.values());
            return "tarea-form"; // Volver al formulario si hay errores
        }
        tareaService.guardar(tarea);
        return "redirect:/tareas"; // Redirigir a la lista después de guardar
    }

    // Eliminar tarea
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminar(id);
        return "redirect:/usuarios"; // Redirigir a la lista después de eliminar
    }

}

