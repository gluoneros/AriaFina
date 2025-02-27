package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Dependencia;
import com.ariafina.crud_tareas.model.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.ariafina.crud_tareas.model.Usuario;
import com.ariafina.crud_tareas.service.UsuarioService;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class VistaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    // Mostrar formulario para crear o editar usuario
    @GetMapping({"/nuevo", "/editar/{id}"})
    public String mostrarFormulario(@PathVariable(required = false) Integer id, Model model) {
        Usuario usuario;
        if (id != null) {
            usuario = usuarioService.obtenerPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        } else {
            usuario = new Usuario();
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("dependencias", Dependencia.values()); // Para el dropdown
        model.addAttribute("perfiles", Perfil.values());         // Para el dropdown
        return "usuario-form"; // Nombre de la plantilla del formulario
    }

    // Guardar o actualizar usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dependencias", Dependencia.values());
            model.addAttribute("perfiles", Perfil.values());
            return "usuario-form"; // Volver al formulario si hay errores
        }
        usuarioService.guardar(usuario);
        return "redirect:/usuarios"; // Redirigir a la lista después de guardar
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios"; // Redirigir a la lista después de eliminar
    }
}