package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.*;
import com.ariafina.crud_tareas.service.FilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.ariafina.crud_tareas.service.UsuarioService;

import java.util.List;

import com.ariafina.crud_tareas.service.TareaService;

@Controller
@RequestMapping("/filas")
public class VistaFilaController {

    @Autowired
    private FilaService filaService;

    //Listar filas
    @GetMapping
    public String listarFilas(Model model) {
        List<Fila> filas = filaService.obtenerTodas();
        model.addAttribute("filas", filas);
        return "filas";
    }








}
