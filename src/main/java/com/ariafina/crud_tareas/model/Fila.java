package com.ariafina.crud_tareas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "filas")
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La tarea es obligatoria.")
    @Min(value = 1, message = "El ID de la tarea debe ser mayor a 0.")
    private Integer tarea;

    @NotNull(message = "La duración es obligatoria.")
    @Min(value = 1, message = "La duración debe ser mayor a 0.")
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Fila() {}

    public Fila(Integer tarea, Integer duracion, Usuario usuario) {
        this.tarea = tarea;
        this.duracion = duracion;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
