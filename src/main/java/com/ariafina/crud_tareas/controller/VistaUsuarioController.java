package com.ariafina.crud_tareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ariafina.crud_tareas.model.Usuario;
import com.ariafina.crud_tareas.service.UsuarioService;
import java.util.List;

@Controller
@RequestMapping("/usuarios") // Cambia el prefijo para vistas
public class VistaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping // Esto manejará GET /usuarios
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Nombre de la plantilla Thymeleaf (usuarios.html)
    }
}