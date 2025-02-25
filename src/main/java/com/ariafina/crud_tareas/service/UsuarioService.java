package com.ariafina.crud_tareas.service;

import com.ariafina.crud_tareas.model.Tarea;
import com.ariafina.crud_tareas.model.Usuario;
import com.ariafina.crud_tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Guardar una nueva tarea
    public Usuario guardar(Usuario usuario) {
        if (usuarioRepository.findByNombre(usuario.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una tarea con este nombre");
        }
        return usuarioRepository.save(usuario);
    }


    // Actualizar una tarea existente
    public Usuario actualizar(Integer id, Tarea nuevaTarea) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    Usuario nuevoUsuario = new Usuario();
                    usuario.setNombre(nuevoUsuario.getNombre());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }


    // Eliminar una tarea por ID
    public void eliminar(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarea con ID " + id + " no encontrada.");
        }
    }

    // Buscar una tarea por nombre
    public Optional<Object> obtenerPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }


}
