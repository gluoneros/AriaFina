package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.*;
import com.ariafina.crud_tareas.service.FilaService;
import com.ariafina.crud_tareas.service.UsuarioService;
import com.ariafina.crud_tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/filas")
public class VistaFilaController {

    @Autowired
    private FilaService filaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TareaService tareaService;

    // Listar filas
    @GetMapping
    public String listarFilas(Model model) {
        List<Fila> filas = filaService.obtenerTodas();
        model.addAttribute("filas", filas);
        return "fila";
    }

    // Mostrar formulario de nueva asignación
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("fila", new Fila());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("tareas", tareaService.obtenerTodas());
        return "fila-form";
    }

    // Guardar nueva asignación
    @PostMapping("/guardar")
    public String guardarFila(@Valid @ModelAttribute("fila") Fila fila, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            model.addAttribute("tareas", tareaService.obtenerTodas());
            return "fila-form";
        }
        filaService.guardar(fila);
        return "redirect:/filas";
    }

    // Eliminar una asignación
    @GetMapping("/eliminar/{id}")
    public String eliminarFila(@PathVariable Integer id) {
        filaService.eliminar(id);
        return "redirect:/filas";
    }
}
