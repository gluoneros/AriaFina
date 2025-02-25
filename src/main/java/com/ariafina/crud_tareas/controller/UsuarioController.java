package com.ariafina.crud_tareas.controller;

import com.ariafina.crud_tareas.model.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import com.ariafina.crud_tareas.model.Usuario;
import com.ariafina.crud_tareas.service.UsuarioService;
import jakarta.validation.Valid;
import com.ariafina.crud_tareas.service.TareaService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

 //   @Autowired
 //   private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}


    // Obtener todas las tareas
    @GetMapping
    public List<Usuario> listarUsuarios() {return usuarioService.listarUsuarios();}


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,@Valid @RequestBody Usuario usuario) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}

